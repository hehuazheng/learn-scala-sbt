package example

import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

object bayesDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("bayes")
    val sc = new SparkContext(conf)
    val data = MLUtils.loadLabeledPoints(sc, "src/test/data/example/bayesDemo.data")
    val model = NaiveBayes.train(data, 1.0)
    model.pi.foreach(println)
    val label = model.predict(Vectors.dense(0,10,0))
    println(label)
  }
}
