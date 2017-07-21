package mllib

import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors

object testVector {
  def main(args: Array[String]): Unit = {
    val vd: linalg.Vector = Vectors.dense(2, 0, 6)
    println(vd(2))

    val vs: linalg.Vector = Vectors.sparse(8, Array(0, 1, 2, 7), Array(9, 5, 2, 7))
    println(vs(7))
  }
}
