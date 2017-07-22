package example

import org.apache.spark.mllib.feature.{HashingTF, IDF}
import org.apache.spark.{SparkConf, SparkContext}

object tfidfDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("tfidf")
    val sc = new SparkContext(conf)
    val data = sc.textFile("src/test/data/example/tfidfDemo.data").map(_.split(' ').toSeq)
    val hashingTf = new HashingTF()
    val tf = hashingTf.transform(data).cache()
    val idf = new IDF().fit(tf)
    val tfIdf = idf.transform(tf)
    tfIdf.foreach(println)
  }
}
