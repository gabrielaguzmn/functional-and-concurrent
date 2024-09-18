package object Circuitos {
  type Chip = List[Int] => List[Int]


  def crearChipBinario(funcion: (Int, Int) => Int): Chip = {
    entrada => List(funcion(entrada.head, entrada(1)))
  }

  

  def halfAdder: Chip = {
    entrada => {
      val sum = entrada.head ^ entrada(1) // XOR para la suma
      val carry = entrada.head & entrada(1) // AND para el acarreo
      List(carry, sum)
    }
  }

  val ha = halfAdder

}
