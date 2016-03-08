import java.io.{FileOutputStream, FileInputStream}

import scala.io.StdIn

/**
 * Created by kdo on 16. 2. 15.
 */
object ReverseQuard extends App {
  Console.setIn(new FileInputStream("/home/kdo/dev/IdeaProjects/algorithm/ReverseQuard/data.txt"))
  //  Console.setOut(new FileOutputStream("/home/kdo/dev/IdeaProjects/algorithm/ReverseQuard/result.txt"))

  /**
   * 케이스 카운트
   */
  val cases = StdIn.readLine().toInt

  def findLeaf(quardString: String) = {
    val strings = quardString.map(_.toString)

    for (index <- 0 until strings.length) {
      if(index == strings.length - 1){

      }
      if(strings(index) == "w" || strings(index) == "b"){

      }
      if(strings(index) == "x" && quardString.s(index + 1)
    }
  }

  def execute(quardString: String) = {
    findLeaf(quardString)
  }

  (1 to cases) foreach { n =>
    print(s"${
      execute(StdIn.readLine())
    }\n")
  }
}
