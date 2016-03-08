import java.io.{FileOutputStream, FileInputStream}
import scala.io.StdIn

/**
 * Created by kdo on 16. 1. 25.
 */
object GameBoard extends App {
  Console.setIn(new FileInputStream("/home/kdo/dev/IdeaProjects/algorithm/GameBoard/data.txt"))
  Console.setOut(new FileOutputStream("/home/kdo/dev/IdeaProjects/algorithm/GameBoard/result.txt"))
  (0 to 10).toStream.takeWhile(p => p > 5).foreach(a => print(a))
  /**
   * 케이스 카운트
   */
  val cases = StdIn.readLine().toInt

  class Point(val x: Int, val y: Int) {
  }

  /**
   * A ..
   * **.
   * B ..
   * ***.
   * C .
   * **..
   * D  .
   * **..
   * @param board
   * @param flag
   * @param point
   * @return
   */
  def getFilledBoard(board: List[Array[String]], flag: String, point: Point): List[Array[String]] = {
    if (flag == "D") board(point.y)(point.x) = "#"
    if (flag == "C") board(point.y)(point.x + 1) = "#"
    if (flag == "B") board(point.y + 1)(point.x) = "#"
    if (flag == "A") board(point.y + 1)(point.x + 1) = "#"
    board
  }

  def isAllFilled(board: List[Array[String]]): Boolean = {
    var result = true
    board.takeWhile(_ => result).foreach {
      row => row foreach {
        column => column
      }
    }
    false
  }

  def execute(height: Int, width: Int, board: List[Array[String]]): Int = {
    var result = 0
    def avail(board: List[Array[String]], flag: String): Boolean = {
      false
    }
    def isInsert(board: List[Array[String]], point: Point): Boolean = {
      List("A", "B", "C", "D").foreach {
        case_ => if (avail(board, case_)) {
          isInsert(getFilledBoard(board, case_, point), point)
        }
      }
      if (isAllFilled(board)) true
      false
    }
    if (isInsert(board, new Point(0, 0))) result += 1
    result
  }
  (1 to cases) foreach { n =>
    print(s"${
      var list: List[Array[String]] = List()
      val Array(height, width) = StdIn.readLine().split(" ").map(_.toInt)

      for (index <- 0 until height) {
        list = list ::: List(StdIn.readLine().split(""))
      }
      execute(height, width, list)
    }\n")
  }
}

//
//import scala.io.StdIn
//
///**
// * Created by kdo on 16. 2. 15.
// */
//object ReverseQuard extends App {
//
//  def execute(quardString: String) = {
//
//  }
//
//  (1 to cases) foreach { n =>
//    print(s"${
//      execute(StdIn.readLine())
//    }\n")
//  }
//}
