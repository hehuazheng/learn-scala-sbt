package example

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkConf, SparkContext}

object svdDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("svd")
    val sc = new SparkContext(conf)
    val data = sc.textFile("src/test/data/example/svdDemo.data")
      .map(_.split(' ').map(_.toDouble)
        .map(line => Vectors.dense(line)))
//    val rm = new RowMatrix(data)

  }
}
