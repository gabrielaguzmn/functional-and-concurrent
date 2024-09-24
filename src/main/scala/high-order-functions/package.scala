package object Circuitos {

  /**
   * Definición de los chips
   */

  type Chip = List[Int] => List[Int]

  /**
    * Definición de las compuertas lógicas sencillas
    */

  val not: Int => Int = x => 1 - x
  val or: (Int, Int) => Int = (x, y) => x + y - (x * y)
  val and: (Int, Int) => Int = (x, y) => (x * y)
  val xor: (Int, Int) => Int = (x, y) => or(and(not(x), y), and(x, not(y)))

  /**
   * Ejercicio 1.1.1
   * Compuerta unaria
  */

  def crearChipUnario(funcion: Int => Int): Chip = {
    entrada => List(funcion(entrada.head))
  }

  /**
    * Ejercicio 1.1.2
    * Compuerta binaria
    */

  def crearChipBinario(funcion: (Int, Int) => Int): Chip = {
    entrada => List(funcion(entrada.head, entrada(1)))
  }

  /**
    * Ejercicio 1.2
    * Semi sumador
    */

  def halfAdder: Chip = {
    entrada => {
      val a = entrada.head
      val b = entrada.tail.head

      val sum = xor(a, b)
      val carry = and(a, b)

      List(carry, sum)
    }
  }

  val ha = halfAdder

  /**
    * Ejercicio 1.3
    * Sumador completo
    */

  def fullAdder: Chip = {
    entrada => {
      val a = entrada.head
      val b = entrada.tail.head
      val cin = entrada.tail.tail.head

      val ha1 = ha(List(a, b))
      val ha2 = ha(List(ha1(1), cin))
      val cout = or(ha1.head, ha2.head)

      List(cout, ha2(1))
    }
  }

  val fa = fullAdder

}
