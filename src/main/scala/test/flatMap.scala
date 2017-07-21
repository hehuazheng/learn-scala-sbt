package test

import org.apache.spark.{SparkConf, SparkContext}

object flatMap {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("flatMap")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5))
    val result = arr.flatMap(x => List(x+1, x+2)).distinct().collect()
    result.foreach(println)
  }
}
