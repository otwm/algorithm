
val quard = "12345678"


val k = List('a','a','b','c','d')

val quardList = List(1,2,3,4,5,6,7,8,9)
val spare = for {
  index <- 0 until quardList.length
  if (index > 3)
} yield {
  quardList(index)
}

spare(0)
