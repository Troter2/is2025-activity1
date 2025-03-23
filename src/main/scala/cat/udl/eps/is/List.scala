package cat.udl.eps.is

import scala.annotation.tailrec

// Exercises in user defined lists

enum List[+A] {
  case Nil
  case Cons(head: A, tail: List[A])
}

object List {

  def apply[A](as: A*): List[A] = {
    // This foldRight is the predefined one on Seq
    // This code does the same as the one in the fpinscala repo
    as.foldRight(Nil: List[A])(Cons.apply)
  }

  @annotation.tailrec
  def foldLeft[A, B](l: List[A], acc: B)(f: (B, A) => B): B = {
    l match
      case Nil         => acc
      case Cons(x, xs) => foldLeft(xs, f(acc, x))(f)
  }

  // If we put a function in a single parameter list we can define the function
  // with {} instead of ()
  def reverse[A](l: List[A]): List[A] = {
    foldLeft(l, Nil: List[A]) { (acc, x) =>
      Cons(x, acc)
    }
  }

  // This version of foldRight is stack save
  def foldRight[A, B](l: List[A], acc: B)(f: (A, B) => B): B = {
    foldLeft(reverse(l), acc) { (acc, x) =>
      f(x, acc)
    }
  }

  def append[A](l: List[A], r: List[A]): List[A] = {
    foldRight(l, r)(Cons.apply)
  }

  def concat[A](l: List[List[A]]): List[A] = {
    foldRight(l, Nil: List[A])(append)
  }

  // ------------------------------------------------------------------

  // partition
  // La primera de les llistes conté els elements que compleixen p, la
  // segona els que no ho fan
  def partition2[A](l: List[A])(p: A => Boolean): (List[A], List[A]) =
    foldRight(l,(Nil,Nil)){(elem, acc)  =>
      val (compleix, no_compleix) = acc
      p(elem) match
        case true => (Cons(elem,compleix),no_compleix)
        case false => (compleix, Cons(elem,no_compleix))
    }
  def partition3[A](l: List[A])(p: A => Boolean): (List[A], List[A]) =

    foldLeft(reverse(l),(Nil,Nil)){(acc, elem)  =>
      val (compleix, no_compleix) = acc
      p(elem) match
        case true => (Cons(elem,compleix),no_compleix)
        case false => (compleix, Cons(elem,no_compleix))
    }

  def partition[A](l: List[A])(p: A => Boolean): (List[A], List[A]) =
    l match
      case List.Nil => (Nil, Nil)
      case List.Cons(head, tail) =>
        val (compleix, no_compleix) = partition(tail)(p)
        if(p(head)) then (Cons(head, compleix), no_compleix) else  (compleix,Cons(head, no_compleix))



  // digitsToNum (use a fold, choose wisely)
  // NOTA: si algun element de la llista d'entrada no és un dígil, llenceu una
  // excepció (useu sys.error("missatge"))
  def digitsToNum(l: List[Int]): Int =
    foldLeft(l,0){ (acc,elem) =>
      if (elem < 0 || elem > 9) sys.error("Un element no és un dígit")
      else elem + acc * 10
    }

  // insertion sort
  // Ordeneu la llista fent servir el mètode d'inserció
  // NOTA: feu servir un mètode auxiliar per inserir un element en una llista ordenada
  def sort[A, B >: A](as: List[A])(using ord: Ordering[B]): List[A] = {
    foldLeft(as, Nil: List[A])((acc, x) => insert(x, acc))
  }

  private def insert[A, B >: A](acc: A, l: List[A])(using ord: Ordering[B]): List[A] = {
    l match
      case Nil => Cons(acc, Nil)
      case Cons(head, tail) =>
        if (ord.lteq(acc, head)) Cons(acc, l)
        else Cons(head, insert(acc, tail))
  }

  // merge sorted
  // Fusionar dous llistes ordenades en una de sola
  def mergeSorted[A, B >: A](l1: List[A], l2: List[A])(using
      ord: Ordering[B]
  ): List[A] = {
    (l1, l2) match
      case (Nil, _) => l2
      case (_, Nil) => l1
      case (Cons(h1, t1), Cons(h2, t2)) =>
        if (ord.lteq(h1, h2)) Cons(h1, mergeSorted(t1, l2))
        else Cons(h2, mergeSorted(l1, t2))
  }

  // check sorted
  // Comproveu si la llista que us passen està ordenada
  @tailrec
  def checkSorted[A, B >: A](as: List[A])(using ord: Ordering[B]): Boolean = {
    as match
      case Nil => true
      case Cons(_, Nil) => true
      case Cons(x, Cons(y, tail)) => ord.lteq(x, y) && checkSorted(Cons(y, tail))
  }

  // find
  // Retornar el primer element de la llista que compeix el predicat.
  // Useu el tipus Option de scala per manegar el cas de no-existència
  @tailrec
  def find[A](l: List[A])(p: A => Boolean): Option[A] = {
    l match
      case Nil => None
      case Cons(head, tail) => if (p(head)) Some(head) else find(tail)(p)
  }

  // partition map
  // En comptes d'un predicat per decidir les particions, usarem el tipus Either de
  // scala per a decidir si els elements van a una llista o a l'altra
  def partitionMap[A, B, C](
      l: List[A]
  )(p: A => Either[B, C]): (List[B], List[C]) = {
    foldRight(l, (Nil: List[B], Nil: List[C])) { (elem, acc) =>
      val (lefts, rights) = acc
      p(elem) match
        case Left(b)  => (Cons(b, lefts), rights)
        case Right(c) => (lefts, Cons(c, rights))
    }
  }

}
