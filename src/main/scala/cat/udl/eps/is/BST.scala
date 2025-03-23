package cat.udl.eps.is

import scala.List

enum BST[+A] {
  case Empty
  case Node(left: BST[A], value: A, right: BST[A])

  // 1. insert
  // Recordeu: el paràmetre ord és l'equivalent al Comparator per a poder
  // comparar elements de tipus A
  
  def insert[B >: A](b: B)(using ord: Ordering[B]): BST[B] = ???

  // 2. contains
  // Indica si l'element es troba dins de l'arbre de cerca.
  def contains[B >: A](a: B)(using ord: Ordering[B]): Boolean = ???

  // 3.fold
  // En funció dels constructors que té el tipus, podeu definir els tipus
  // dels paràmetres del fold i el codi és 'simplement' la implementació de
  // la recursivitat estructural sobre el mateix.
  def fold[B](b: B)(f: (B, A, B) => B): B = ???
}

object BST {

  // 4. fromList (via foldXXXX)
  // Podeu fer també versions addicionals recursives
  // NOTA: El tipus llista es correspon a les de la biblioteca standard.
  def fromList[A](l: List[A])(using ord: Ordering[A]): BST[A] = ???

  // 5. inorder (via fold)
  // Podeu fer també versions addicionals recursives
  // NOTA: El tipus llista es correspon a les de la biblioteca standard.
  def inorder[A](t: BST[A]): List[A] = ???

}
