package test

import org.apache.spark.{SparkConf, SparkContext}

object testRddMethod {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testRddMethod")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6), 2)
    val result = arr.aggregate(0)(math.max(_, _), _+_)
    println(result)
  }
}
