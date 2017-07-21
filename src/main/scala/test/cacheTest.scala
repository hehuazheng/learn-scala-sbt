package test

import org.apache.spark.{SparkConf, SparkContext}

object cacheTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("cacheTest")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array("abc", "b", "c", "d", "e", "f"))
    println(arr)
    arr.foreach(println)
  }
}
