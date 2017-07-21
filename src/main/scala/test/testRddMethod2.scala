package test

import org.apache.spark.{SparkConf, SparkContext}

object testRddMethod2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testRddMethod2")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array("abc", "b", "c", "d", "e", "f"))
    val result = arr.aggregate("")((value, word) => value + word, _+_)
    println(result)
  }
}
