package spark

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.{ColumnPrefixFilter, FilterList}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.{Base64, Bytes}
import org.apache.hadoop.hbase.{CellUtil, HBaseConfiguration}
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object hbaseRddDemo {
  private val LOG = LoggerFactory.getLogger(hbaseRddDemo.getClass)

  def main(args: Array[String]): Unit = {
    val savePath = args(0)
    LOG.info("savepath is : " + savePath)
    val conf = HBaseConfiguration.create
    val zookeeperHosts: String = "center1.xxx.com:2181,center2.xxx.com:2181,center3.xxx.com:2181"
    conf.set("hbase.zookeeper.quorum", zookeeperHosts)
    conf.set("zookeeper.session.timeout", "600000")
    conf.set("zookeeper.znode.parent", "/hbase-unsecure")
    conf.set("hbase.client.scanner.timeout.period", "1200000")
    conf.set(TableInputFormat.INPUT_TABLE, "hzz_test")
    conf.set(TableInputFormat.SCAN, {
      val scan = new Scan
      scan.setCacheBlocks(false)
      scan.setCaching(500)
      scan.addFamily("data".getBytes)
      //filter 列表
      val filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE)
      filterList.addFilter(new ColumnPrefixFilter("p".getBytes()))
      filterList.addFilter(new ColumnPrefixFilter("o".getBytes()))
      scan.setFilter(filterList)

      val proto = ProtobufUtil.toScan(scan)
      Base64.encodeBytes(proto.toByteArray)
    })

    val spark = SparkSession.builder().appName("hzz-test").getOrCreate()
    val sc = spark.sparkContext
    //    sc.getConf.registerKryoClasses()
    val rdd = sc.newAPIHadoopRDD(conf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[org.apache.hadoop.hbase.client.Result])
      .map {
        case (_, result) =>
          var items: List[(String, String)] = List()
          while (result.advance()) {
            val cell = result.current()
            val rowKey = Option(Bytes.toString(CellUtil.cloneRow(cell))).getOrElse("NULL")
            val colName = Option(Bytes.toString(CellUtil.cloneQualifier(cell))).getOrElse("NULL")
            val colValue = Option(Bytes.toString(CellUtil.cloneValue(cell))).getOrElse("NULL")
            items = items :+ (s"${rowKey}", s"${colName}-${colValue}")
          }
          items
      }.flatMap(x => x).mapPartitions(itr => itr.map(x => x))
    val fs = FileSystem.get(rdd.sparkContext.hadoopConfiguration)
    val targetPath = new Path(savePath)
    rdd.saveAsTextFile(targetPath.toString)
  }
}
