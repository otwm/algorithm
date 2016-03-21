import java.io.FileInputStream

import scala.io.StdIn

/**
  * Created by kdo on 16. 3. 21.
  */
object Fence extends App {
  Console.setIn(new FileInputStream("/data/1/dev/IdeaProjects/algorithm/fence/resources/data.txt"))

  val cases = StdIn.readLine().toInt

  def execute(length: Int, fences: List[Int]): Int = {
    var result = -1
    for (index <- 0 until length) {
      val width: Int = {
        val temp = fences(index);
        def getRight(current: Int): Int = {
          if (current == length) return length
          if (fences(current) < temp || (current + 1) == length) {
            return current - 1
          }
          getRight(current + 1)
        }
        val right = getRight(index + 1)

        def getLeft(current: Int): Int = {
          if (current < 0) return 0
          if (fences(current) < temp || current == 0) {
            return current + 1
          }
          getLeft(current - 1)
        }
        val left = getLeft(index - 1)

        right - left + 1
      }
      val height = fences(index)
      if (result < width * height) {
        result = width * height
      }
    }
    result
  }

  (1 to cases) foreach { n =>
    print(s"${
      execute(
        StdIn.readLine().toInt
        , StdIn.readLine().split(" ").map(_.toInt).toList)
    }\n")
  }
}
