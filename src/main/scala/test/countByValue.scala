package test

import org.apache.spark.{SparkConf, SparkContext}

object countByValue {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("countByValue")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    val result = arr.countByValue()
    result.foreach(print)
  }
}
