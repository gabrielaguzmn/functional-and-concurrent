package object Circuitos {
  type Chip = List[Int] => List[Int]
  object Circuitos{
    def not(x:Int): Int = 1-x
    def or(x:Int, y:Int ): Int = x + y - y * y
    def and(x:Int, y:Int): Int= x * y
    def xor(x:Int, y:Int): Int= or(and(not(x),y),and(x,not(y)))
  }

  def crearChipBinario(funcion: (Int, Int) => Int): Chip = {
    entrada => List(funcion(entrada.head, entrada(1)))
  }


  def halfAdder: Chip = {
    entrada => {
      val a = entrada.head
      val b = entrada(1)

      // Usa las funciones de Circuitos para calcular la suma (S) y el acarreo (C)
      val sum = Circuitos.xor(a, b) // XOR usando la implementación de Circuitos
      val carry = Circuitos.and(a, b) // AND usando la implementación de Circuitos

      List(carry, sum)
    }
  }

  val ha = halfAdder

}
