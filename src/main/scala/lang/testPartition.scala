package lang

import org.apache.spark.{SparkConf, SparkContext}

object testPartition {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[3]").setAppName("testPartition")
    val sc = new SparkContext(conf)

    val seq = (1 to 100).toSeq
    val count = sc.parallelize(seq).mapPartitionsWithIndex {
      (x,iter) =>
        val result = List[String]()
        val str = iter.foldLeft("") {
          (b, a) => s"$b,$a"
        }
        println(s"$x - $str")
        result.iterator
    }.collect()
    println(s"count is ${count.length}")
    sc.stop()
  }
}
