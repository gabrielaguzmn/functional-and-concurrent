package object Circuitos {
  type Chip = List[Int] => List[Int]
  val not: Int => Int = x => 1 - x
  val or: (Int, Int) => Int = (x, y) => x + y - (x * y)
  val and: (Int, Int) => Int = (x, y) => (x * y)
  val xor: (Int, Int) => Int = (x, y) => or(and(not(x), y), and(x, not(y)))

  /*object Circuitos{
    def not(x:Int): Int = 1-x
    def or(x:Int, y:Int ): Int = x + y - (x * y)
    def and(x:Int, y:Int): Int= (x * y)
    def xor(x:Int, y:Int): Int= or(and(not(x),y),and(x,not(y)))
  }*/

  def crearChipBinario(funcion: (Int, Int) => Int): Chip = {
    entrada => List(funcion(entrada.head, entrada(1)))
  }


  def halfAdder: Chip = {
    entrada => {
      val a = entrada.head
      val b = entrada(1)

      // Suma (XOR) y Acarreo (AND) usando las funciones actuales
      val sum = xor(a, b) // XOR usando Circuitos
      val carry = and(a, b) // AND usando Circuitos

      List(carry, sum)
    }
  }

  val ha = halfAdder

}
