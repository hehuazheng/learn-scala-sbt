package test

import org.apache.spark.{SparkConf, SparkContext}

object wordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wordCount")
    val sc = new SparkContext(conf)
    val data = sc.textFile("test.txt")
    data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).collect().foreach(println)
  }
}