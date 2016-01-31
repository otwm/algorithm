import java.io.{FileOutputStream, FileInputStream}
import scala.io.StdIn

/**
 * Created by kdo on 16. 1. 25.
 */
object GameBoard extends App {
  Console.setIn(new FileInputStream("/home/kdo/dev/IdeaProjects/algorithm/GameBoard/data.txt"))
  //  Console.setOut(new FileOutputStream("/home/kdo/dev/IdeaProjects/algorithm/GameBoard/result.txt"))
  /**
   * 케이스 카운트
   */
  val cases = StdIn.readLine().toInt

  class Point(val x: Int, val y: Int, board: List[Array[String]]) {
    val length = board(y).length - 1

    def forward(): Point = {
      x match {
        case this.length => new Point(0, y + 1, board)
        case _ => new Point(x + 1, y, board)
      }
    }
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
    val result = for {
      index <- 0 until board.size
      row = board(index)
    } yield {
      if (index == point.y) row.updated(point.x, "#")
      row
    }
    Nil ++ result
  }


  //    var _board = board :: Nil
  //    if (flag != "D") _board(point.y)(point.x) = "#"
  //    if (flag != "C") _board(point.y)(point.x + 1) = "#"
  //    if (flag != "B") _board(point.y + 1)(point.x) = "#"
  //    if (flag != "A") _board(point.y + 1)(point.x + 1) = "#"
  //    _board
  //  }

  def isAllFilled(board: List[Array[String]]): Boolean = {
    !board.exists(x => x.exists(y => y == "."))
  }

  def execute(height: Int, width: Int, board: List[Array[String]]): Int = {
    var result: Int = 0
    /**
     * 블록 삽입 가능 여부
     * @param board
     * @param flag
     * @return
     */
    def avail(board: List[Array[String]], flag: String, point: Point): Boolean = {
      if (board(point.y).length == point.x + 1) return false
      flag match {
        case "A" =>
          if (board(point.y)(point.x) == "#") return false
          if (board(point.y)(point.x + 1) == "#") return false
          if (board(point.y + 1)(point.x) == "#") return false
          return true
        case "B" =>
          if (board(point.y)(point.x) == "#") return false
          if (board(point.y)(point.x + 1) == "#") return false
          if (board(point.y + 1)(point.x + 1) == "#") return false
          return true
        case "C" =>
          if (board(point.y)(point.x) == "#") return false
          if (board(point.y + 1)(point.x) == "#") return false
          if (board(point.y + 1)(point.x + 1) == "#") return false
          return true
        case "D" =>
          if (board(point.y)(point.x + 1) == "#") return false
          if (board(point.y + 1)(point.x) == "#") return false
          if (board(point.y + 1)(point.x + 1) == "#") return false
          return true
        case _ => return true
      }
    }


    def isInsert(board: List[Array[String]], point: Point): Boolean = {
      List("A", "B", "C", "D").foreach { case_ =>
        if (avail(board, case_, point)) {
          isInsert(getFilledBoard(board, case_, point), point.forward())
        } else if (case_ == "D") {
          if (point.x == 1) return false
          if (point.y == board.size - 2 && point.x == board(board.size - 1).length - 2) {
            return false
          }
          if (isAllFilled(board)) {
            result += 1
            return true
          }
          isInsert(board, point.forward())
        }
      }
      if (isAllFilled(board)) {
        result += 1
        return true
      }
      false
    }

    if (isInsert(board, new Point(0, 0, board))) result += 1
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
