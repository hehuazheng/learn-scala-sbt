package test

import org.apache.spark.{SparkConf, SparkContext}

object testReduce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testReduce")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array("one", "two", "three"))
    val result = str.reduce(_ + _)
    println(result)
  }
}
