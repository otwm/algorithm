import java.io.{FileInputStream, FileOutputStream}
import java.util

import scala.io.StdIn

/**
  * Created by kdo on 16. 2. 15.
  */
object Main extends App {
//  Console.setIn(new FileInputStream("/data/1/dev/IdeaProjects/algorithm/ReverseQuard/resources/data.txt"))
  val cases = StdIn.readLine().toInt

  def process(quard: List[String], index: Int): List[String] = {
    val pre = quard.slice(0, index) ::: List("x" + quard(index + 3) + quard(index + 4) + quard(index + 1) + quard(index + 2))
    if (quard.length == index + 4) {
      return pre
    }
    pre ::: quard.slice(index + 5, quard.length)
  }

  def findProcessable(quard: List[String]): Int = {
    for (index <- 0 until quard.length) {
      if (
        index + 4 <= quard.length
          && quard(index) == "x"
          && quard(index + 1) != "x"
          && quard(index + 2) != "x"
          && quard(index + 3) != "x"
          && quard(index + 4) != "x"
      ) {
        return index
      }
    }
    -1
  }

  def execute(quard: List[String]): List[String] = {
    var index = findProcessable(quard)
    var result: List[String] = quard
    while (index >= 0) {
      result = process(result, index)
      index = findProcessable(result)
    }
    result
  }

  (1 to cases) foreach { n =>
    print(s"${
      execute(StdIn.readLine().map(_.toString).toList).mkString
    }\n")
  }
}
