package test

import org.apache.spark.{SparkConf, SparkContext}

object coalesce {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("coalesce")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    val arr2 = arr.coalesce(2, true)
    val result = arr.aggregate(0)(math.max(_, _), _+_)
    println(result)
    val result2 = arr2.aggregate(0)(math.max(_, _), _+_)
    println(result2)
  }
}
