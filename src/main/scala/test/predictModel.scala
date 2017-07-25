package test

import org.apache.spark.{SparkConf, SparkContext}

object predictModel {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("predict")
    val sc = new SparkContext(conf)

    val data = sc.textFile("d:/share/predict_result.model")
    val tuples = data.map(s => {
      val arr = s.split('|')
      (arr(0), arr(1) + ":" + arr(2))
    }).aggregateByKey("")((e1, e2) => e1 + "|" + e2, (v1, v2) => v1 + "," + v2)
    val maxLength = tuples.map( s => (s._1, s._2.split('|').length)).reduce((v1, v2) => {
      if(v1._2 > v2._2) v1
      else v2
    })
    println(maxLength._1 + " " + maxLength._2)
//    maxLength._2
//    tuples.foreach(println)
    tuples.filter(_._1.equals("gucci")).foreach(println)
    sc.stop()
  }
}
