package mllib

import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint

object testLabeledPoint {
  def main(args: Array[String]): Unit = {
    val vd: Vector = Vectors.dense(2, 0, 6)
    val pos = LabeledPoint(1, vd)
    println(pos.features)
    println(pos.label)

    val vs: Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(9, 5, 2, 7))
    val neg = LabeledPoint(2, vs)
    println(neg.features)
    println(neg.label)
  }
}
