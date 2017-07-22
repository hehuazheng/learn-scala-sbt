package example

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.{SparkConf, SparkContext}

//y=2x1+3x2
object linearRegression {
  val conf = new SparkConf().setMaster("local").setAppName("linearRegression")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    val data = sc.textFile("src/test/data/lpsa2.data")
    val parsedData = data.flatMap(x => List(x, x, x, x, x)).map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }.cache()
    val model = LinearRegressionWithSGD.train(parsedData, 200, 0.01)
    println(model.weights)
    val result = model.predict(Vectors.dense(2, 1))
    println(result)
  }
}
