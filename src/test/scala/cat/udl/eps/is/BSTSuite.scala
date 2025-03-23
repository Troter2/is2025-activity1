package cat.udl.eps.is

import munit.FunSuite
import scala.List
import BST.*

class BSTSuite extends FunSuite {

  test("contains empty") {
    val t = Empty
    assertEquals(t.contains(1), false)
  }

  test("contains singleton") {
    val t = Node(Empty, 1, Empty)
    assertEquals(t.contains(1), true)
  }

  test("inorder returns the elements sorted") {
    val l = List(3, 2, 1, 4)
    val t = fromList(l)
    assertEquals(inorder(t), l.sorted)
  }
}
