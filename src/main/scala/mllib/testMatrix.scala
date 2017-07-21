package mllib

import org.apache.spark.ml.linalg.Matrices

object testMatrix {
  def main(args: Array[String]): Unit = {
    val mx = Matrices.dense(2, 3, Array(1,2,3,4,5,6))
    println(mx)
  }
}
