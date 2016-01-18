import java.io.{FileOutputStream, FileInputStream}

import scala.io.StdIn

/**
 * 피크닉 문제 해법
 * Created by kdo on 16. 1. 18.
 */
object Picnic extends App {
  Console.setIn(new FileInputStream("/home/kdo/dev/IdeaProjects/algorithm/picnic/data.txt"))
  Console.setOut(new FileOutputStream("/home/kdo/dev/IdeaProjects/algorithm/picnic/result.txt"))

  /**
   * 케이스 카운트
   */
  val cases = StdIn.readLine().toInt

  /**
   * 문제 풀이
   * @param total 전체 갯수
   * @param pairCount 쌍 카운트
   * @param pairList 쌍 데이터
   * @return 결과
   */
  def execute(total: Int, pairCount: Int, pairList: Array[String]): Int = {
    var result: Int = 0
    val pairDatas: List[(Int, Int)] = {
      var result: List[(Int, Int)] = Nil
      for (index <- 0 until pairList.length by 2) {
        result = result :+ ((pairList(index).toInt, pairList(index + 1).toInt))
      }
      result
    }

    /**
     * 재귀 카운트 프로세스
     * @param myClass 수집된 데이터
     * @return
     */
    def process(myClass: List[Int]): Int = {
      val remain = Range(0, total).diff(myClass)

      val source = remain.head
      val targets = remain.tail

      targets foreach { target =>
        val isFriend = pairDatas.find(element =>
          (element._1 == source && element._2 == target)
            || (element._1 == target && element._2 == source)
        ).isDefined
        if (isFriend) {
          var _myClass = myClass :+ source
          _myClass = _myClass :+ target
          if (_myClass.size == total) {
            result = result + 1
            return 1 //루트 케이스
          }
          process(_myClass)
        }
      }
      result
    }
    result = process(Nil)
    result
  }

  (1 to cases) foreach { n =>
    print(s"${
      val Array(total, pairCount) = StdIn.readLine().split(" ").map(_.toInt)
      val pairList = StdIn.readLine().split(" ")
      execute(total, pairCount, pairList)
    }\n")
  }
}