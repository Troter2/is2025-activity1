package cat.udl.eps.is

import scala.List

enum BST[+A] {
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert
  // Recordeu: el paràmetre ord és l'equivalent al Comparator per a poder
  // comparar elements de tipus A
  
  def insert[B >: A](b: B)(using ord: Ordering[B]): BST[B] = this match {
    case Empty => Node(Empty, b, Empty)
    case Node(left, value, right) =>
      if (ord.lt(b, value)) Node(left.insert(b), value, right)
      else if (ord.gt(b, value)) Node(left, value, right.insert(b))
      else this // ja existeix, no fem res
  }

  // 2. contains
  // Indica si l'element es troba dins de l'arbre de cerca.
  def contains[B >: A](a: B)(using ord: Ordering[B]): Boolean = this match {
    case Empty => false
    case Node(left, value, right) =>
      if (ord.equiv(a, value)) true
      else if (ord.lt(a, value)) left.contains(a)
      else right.contains(a)
  }

  // 3.fold
  // En funció dels constructors que té el tipus, podeu definir els tipus
  // dels paràmetres del fold i el codi és 'simplement' la implementació de
  // la recursivitat estructural sobre el mateix.
  def fold[B](b: B)(f: (B, A, B) => B): B = this match {
    case Empty => b
    case Node(left, value, right) => f(left.fold(b)(f), value, right.fold(b)(f))
  }
}

object BST {

  // 4. fromList (via foldXXXX)
  // Podeu fer també versions addicionals recursives
  // NOTA: El tipus llista es correspon a les de la biblioteca standard.
  def fromList[A](l: List[A])(using ord: Ordering[A]): BST[A] =
    l.foldLeft(BST.Empty: BST[A])((tree, elem) => tree.insert(elem))


  // 5. inorder (via fold)
  // Podeu fer també versions addicionals recursives
  // NOTA: El tipus llista es correspon a les de la biblioteca standard.
  def inorder[A](t: BST[A]): List[A]  =
    t match {
      case BST.Empty => List.empty
      case BST.Node(left, value, right) =>
        inorder(left) ::: (value :: inorder(right))
    }
}