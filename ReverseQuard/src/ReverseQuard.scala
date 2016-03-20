import java.io.{FileOutputStream, FileInputStream}

import scala.io.StdIn

/**
  * Created by kdo on 16. 2. 15.
  */
object ReverseQuard extends App {
  Console.setIn(new FileInputStream("/data/1/dev/IdeaProjects/algorithm/ReverseQuard/resources/data.txt"))
  val cases = StdIn.readLine().toInt

  def reverse(quard: List[String]): String = {
    if (quard.length < 4) {
      return quard.mkString("")
    }
    return quard(2) + quard(3) + quard(0) + quard(1)
  }

  class Result(val head: Option[String], val middle: Option[String], val tail: Option[String]) {
  }

  def execute(quard: String): List[String] = {
    val removedx = {
      if (quard.startsWith("x")) {
        quard.substring(1, quard.length)
      } else {
        quard
      }
    }
    if (removedx.indexOf("x") < 0) {
      if (quard.length < 4) {
        return List(removedx)
      }
      if (quard.length == 4) {
        return List("x" + reverse(removedx.map(_.toString).toList))
      }
      if (removedx.length > 4) {
        return List(
          "x" + reverse(removedx.substring(0, 4).map(_.toString).toList)
          , removedx.substring(4, removedx.length)
        )
      }
    }

    val head = removedx.substring(0, removedx.indexOf("x"))
    val tail = removedx.substring(removedx.indexOf("x") + 1, removedx.length)
    val executed = execute(tail)

    def quardList: List[String] = {
      var temp: List[String] = Nil
      temp = temp ::: head.map(_.toString).toList
      temp = temp ::: executed
      if (executed.length == 2) {
        executed(1).map(_.toString).toList
      }
      return temp
    }

    if (quardList.length == 4) {
      List("x" + reverse(quardList))
    } else {
      var spare: List[String] = Nil
      for {
        index <- 0 until quardList.length
        if (index > 3)
      } {
        spare = spare ::: List(quardList(index))
      }
      List("x" + reverse(quardList)) ::: spare
    }
  }

  (1 to cases) foreach { n =>
    print(s"${
      execute(StdIn.readLine())
    }\n")
  }
}
