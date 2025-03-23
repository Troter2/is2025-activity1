package cat.udl.eps.is

import munit.FunSuite
import scala.List
import StdLib.countLengths
import StdLib.sumFirstPowersOf

class StdLibSuite extends FunSuite {
  test("countLengths") {
    val l = List("test","test","test","test","patata","patata")
    val values = List((4,4), (6, 2))
    val myMap = values.toMap
    assertEquals(countLengths(l), myMap)
  }
  test("sumFirstPowersOf") {
    assertEquals(sumFirstPowersOf(2)(4), 15L)
  }
}
