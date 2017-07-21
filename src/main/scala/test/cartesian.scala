package test

import org.apache.spark.{SparkConf, SparkContext}

object cartesian {
  //(1,6)(1,5)(1,4)(1,3)(1,2)(1,1)(2,6)(2,5)(2,4)(2,3)(2,2)(2,1)(3,6)(3,5)(3,4)(3,3)(3,2)(3,1)(4,6)(4,5)(4,4)(4,3)(4,2)(4,1)(5,6)(5,5)(5,4)(5,3)(5,2)(5,1)(6,6)(6,5)(6,4)(6,3)(6,2)(6,1)
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("cartesian")
    val sc = new SparkContext(conf)
    var arr = sc.parallelize(Array(1,2,3,4,5,6))
    var arr2 = sc.parallelize(Array(6,5,4,3,2,1))
    val result = arr.cartesian(arr2)
    result.foreach(print)
  }
}
