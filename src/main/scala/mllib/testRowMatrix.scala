package mllib

import org.apache.spark.{SparkConf, SparkContext}

object testRowMatrix {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testRowMatrix")
    val sc = new SparkContext(conf)
//    val rdd = sc.textFile("src/test/data/svm.txt").map(_.split(' ')).map(_ => Double.parseDouble(_))
//      .map(line => Vectors.dense(line))
  }
}
