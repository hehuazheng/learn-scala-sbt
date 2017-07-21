package test

import org.apache.spark.{SparkConf, SparkContext}
//zip 取多个数组最小长度合并
//(1,a)(2,b)(3,c)
object testZip {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testZip")
    val sc = new SparkContext(conf)
    val arr1 = Array(1,2,3)
    val arr2 = Array("a", "b","c", "d")
    val arr4 = arr1.zip(arr2)
    arr4.foreach(print)
  }
}
