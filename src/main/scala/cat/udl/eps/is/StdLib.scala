package cat.udl.eps.is

import scala.List
import scala.annotation.tailrec

/*
  - (1 to 5) = 1, 2, 3, 4, 5
  - (1 until 5) = 1, 2, 3, 4
  - Iterator
    def iterate[A](a: A)(f: A -> A): Iterator[A]
  - Iterable[A]
    def toList: List[A]
  - List[Int]
    def sum: List[Int]
    def prod: List[Int]
    def sortBy[B](f: A => B)(given ord: Ordering[B]): List[A]
  - List[List[A]]
    def flatten: List[A]
  - List[A]
    def head: A
    def tail: List[A]
    def tails: Iterator[List[A]]
    def map[B](f: A => B): List[B]
    def flatMap[B](f: A => List[B]): List[B]
    def filter(p: A => Boolean): List[A]
    def forall(p: A => Boolean): Boolean
    def exist(p: A => Boolean): Boolean
    def take(n: Int): List[A]
    def drop(n: Int): List[A]
    def takeWhile(p: A => Boolean): List[A]
    def dropWhile(p: A => Boolean): List[A]
    def groupBy[K](f: A => K): Map[K, List[A]]
    def groupMapReduce[K, B](key: A => K)(f: A => B)(reduce: (B, B) => B): Map[K, B]
  - Map[K, V]
    def map[K2, V2](f: ((K, V)) => (K2, V2)): Map[K2, V2]
    def values: Iterable[V]
  - (A, B) // Tuple
     t._1 = t(0) = A
     t._2 = t(1) = B
 */

object StdLib {

  // The result is a map that, for each length, returns the
  // number of words of this particular length
  def countLengths(words: List[String]): Map[Int, Int] = {
    def helper(list: List[String],acc:Map[Int,Int]): Map[Int, Int] = list match
      case ::(head, tail) =>
        val updatedAcc = acc + (head.length -> (acc.getOrElse(head.length, 0) + 1))
        helper(tail, updatedAcc)
      case Nil => acc
    helper(words, Map())
  }

  // b^0 + b^1 + b^2 + ... + b^(n-1)
  // NOTA: No feu servir math.pow doncs hi ha problemes d'arrodoniment
  def sumFirstPowersOf(b: Int)(n: Int): Long = {
    def power(base: Int, exp: Int): Long = {
      if (exp == 0) 1
      else base * power(base, exp - 1)
    }

    @tailrec
    def helper(exp: Int, acc: Long): Long = {
      if (exp == n) acc
      else helper(exp + 1, acc + power(b, exp))
    }

    helper(0, 0L)
  }


}
