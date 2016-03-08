

val kk = for (
  index <- 0 to 10
) yield {
    1
  }

Nil ++ kk
val board: List[Array[String]] = Array("#", ".", "#") :: Array("#", ".", "#") :: Array("#", ".", "#") :: Nil
board
val t:List[Array[String]] = (for {
  index <- 0 until board.size
  row = board(index)
} yield {
  if(index == 0) row.updated(1,"#")
  else row
})(collection.breakOut)

t
t(0)
//val k = Nil ++  t
//k(0)




