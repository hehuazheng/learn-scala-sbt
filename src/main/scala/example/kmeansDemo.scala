package example

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

object kmeansDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2*]").setAppName("kmeansDemo")
    val sc = new SparkContext(conf)

    val data = sc.textFile("src/test/data/example/kmeansDemo.data")
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble)))

    val numClusterrs = 2
    val numIterations = 20
    val model = KMeans.train(parsedData, numClusterrs, numIterations)

    println("cluster centers")
    for(c <- model.clusterCenters) {
      println(" " + c.toString)
    }

    val cost = model.computeCost(parsedData)
    println("within set sum of squared error: " + cost)

    println("Vectors 0.2 0.2 0.2 is belongs to clusters:" + model.predict(Vectors.dense("0.2 0.2 0.2".split(' ').map(_.toDouble))))
    println("Vectors 0.25 0.25 0.25 is belongs to clusters:" + model.predict(Vectors.dense("0.25 0.25 0.25".split(' ').map(_.toDouble))))
    println("Vectors 8 8 8 is belongs to clusters:" + model.predict(Vectors.dense("8 8 8".split(' ').map(_.toDouble))))
  }
}
