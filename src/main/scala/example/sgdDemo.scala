package example

import scala.collection.mutable.HashMap

object sgdDemo {
  val data = HashMap[Int, Int]()

  def getData(): HashMap[Int, Int] = {
    for (i <- 1 to 50) {
      data += (i -> i * 2)
    }
    data
  }

  var theta: Double = 0
  var learnRate: Double = 0.05

  def sgd(x: Double, y: Double) = {
    theta = theta - learnRate * (theta * x - y)
  }

  def main(args: Array[String]): Unit = {
    val dataSource = getData()
    dataSource.foreach(s => sgd(s._1, s._2))
    println(theta)
  }
}
