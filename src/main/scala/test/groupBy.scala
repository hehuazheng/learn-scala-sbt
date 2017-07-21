package test

import org.apache.spark.{SparkConf, SparkContext}

object groupBy {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("groupBy")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5))
    val g1 = arr.groupBy(myFilter(_), 1)
    g1.foreach(println)
    println("------")
    val g2 = arr.groupBy(myFilter2(_), 2)
    g2.foreach(println)
  }

  def myFilter(num: Int): Unit = {
    num >= 3
  }

  def myFilter2(num: Int): Unit = {
    num < 3
  }
}
