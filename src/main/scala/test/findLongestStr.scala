package test

import org.apache.spark.{SparkConf, SparkContext}

object findLongestStr {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("findLongestStr")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array("one", "two", "three", "four"))
    val result = str.reduce(myFun)
    println(result)
  }

  def myFun(str1: String, str2: String):String = {
    if(str2.size > str1.size) {
      return str2
    }
    return str1
  }
}
