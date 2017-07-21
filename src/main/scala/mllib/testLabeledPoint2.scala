package mllib

import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.{SparkConf, SparkContext}

object testLabeledPoint2 {
  def main(args: Array[String]): Unit = {
   val conf = new SparkConf().setMaster("local").setAppName("testLabeldPoint2")
   val sc = new SparkContext(conf)

    val mu = MLUtils.loadLibSVMFile(sc, "src/test/data/svm.txt")
    mu.foreach(println)
  }
}
