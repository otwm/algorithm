import java.io.{FileOutputStream, FileInputStream}

import scala.io.StdIn

/**
 * Created by kdo on 16. 1. 18.
 */
object picnic extends App {
  Console.setIn(new FileInputStream("/home/kdo/dev/IdeaProjects/algorithm/picnic/data.txt"))

  val cases = StdIn.readLine().toInt

  def execute(total: Int, pairCount: Int, pairList: Array[String]) = {
    var result: Int = 0
    var list: List[(Int, Int)] = Nil
    for (index <- 0 until pairList.size by 2) {
      list = list :+ ((pairList(index).toInt, pairList(index + 1).toInt))
    }

    def process(myClass: List[Int]): Int = {
      val source = 0
      val target = total
      val remain = Range(0, total).diff(myClass)

      for (
        person <- remain
      ) {

      }

      (remain) foreach { person =>
        val isFriend = list.find(element =>
          (element._1 == person && element._2 == person + 1)
            && (element._1 == person + 1 && element._2 == person)
        ).size == 0
        if (isFriend) {
          var _myClass = myClass :+ person
          _myClass = _myClass :+ person + 1
          if (_myClass.size == total) {
            println(_myClass)
            return 1
          }
          result = result + process(_myClass)
        }
      }
      //
      result
    }
    result = result + process(Nil)
    result
  }

  (1 to cases) foreach { n =>
    print(s"Case #$n: ${
      val Array(total, pairCount) = StdIn.readLine().split(" ").map(_.toInt)
      val pairList = StdIn.readLine().split(" ")
      execute(total, pairCount, pairList)
    }\n")
  }
}