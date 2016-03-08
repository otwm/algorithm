val a = "xbwxwbbwb"
val b = "xbwxbwwxb"


a.substring(a.indexOf("x") + 1, 4 + 1).indexOf("x")
a.substring(a.indexOf("x") + 1, a.length)

if (a.substring(a.indexOf("x") + 1, 4 + 1).indexOf("x") > 0) {

}

a

def findLeaf(s: String): String = {
  if (s.substring(s.indexOf("x") + 1, 4 + 1).indexOf("x") >= 0) {
    findLeaf(s.substring(s.indexOf("x") + 1, s.length))
  } else {
    s.substring(s.indexOf("x") + 1, s.indexOf("x") + 4 + 1)
  }
}


def swap(s: String) = {
  "" + s(2) + s(3) + s(0) + s(1)
}

swap(findLeaf(a))


def execute(s: String): String = {
  if (s.substring(s.indexOf("x") + 1, 4 + 1).indexOf("x") >= 0) {
    var result = ""
    result += s.splitAt(s.indexOf("x") + 1)._1
    result += execute(s.substring(s.indexOf("x") + 1, s.length))
    result += s.splitAt(s.indexOf("x") + 4 + 1)._2
    result
  } else {
    swap(s.substring(s.indexOf("x") + 1, s.indexOf("x") + 4 + 1))
  }
}

execute(a)

a.splitAt(a.indexOf("x") + 1)._1

a.splitAt(a.indexOf("x") + 4 + 1)._2