import scala.io.StdIn

/**
  * Created by kdo on 16. 3. 21.
  */
object Main extends App {
  val cases = StdIn.readLine().toInt

  def execute(length: Int, fences: List[Long]): Long = {
    var result = -1L
    for (index <- 0 until length) {
      val width: Long = {
        val temp = fences(index);
        def getRight(current: Int): Long = {
          if (current == length) return length
          if (fences(current) < temp || (current + 1) == length) {
            return current - 1
          }
          getRight(current + 1)
        }
        val right = getRight(index + 1)

        def getLeft(current: Int): Long = {
          if (current < 0) return 0
          if (fences(current) < temp || current == 0) {
            return current + 1
          }
          getLeft(current - 1)
        }
        val left = getLeft(index - 1)

        right - left + 1L
      }
      val height: Long = fences(index)
      val value: Long = width * height
      if (result < value) {
        result = value
      }
    }
    result
  }

  (1 to cases) foreach { n =>
    print(s"${
      execute(
        StdIn.readLine().toInt
        , StdIn.readLine().split(" ").map(_.toLong).toList)
    }\n")
  }
}



