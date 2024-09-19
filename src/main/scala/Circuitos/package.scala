package object Circuitos {
  type Chip = List[Int] => List[Int]
  val not: Int => Int = x => 1 - x
  val or: (Int, Int) => Int = (x, y) => x + y - (x * y)
  val and: (Int, Int) => Int = (x, y) => (x * y)
  

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

      // Suma  y Acarreo (AND) usando las funciones actuales
      val sum = or(and(a, not(b)), and(not(a), b))
      val carry = and(a, b) // AND usando Circuitos

      List(carry, sum)
    }
  }

  val ha = halfAdder

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


  def adder(n: Int): Chip = {
    def sumaBits(primerNumero: List[Int], segundoNumero: List[Int], carryIn: Int, acc: List[Int]): List[Int] = {
      if (primerNumero.isEmpty) carryIn :: acc
      else {
        val b1 = primerNumero.head
        val b2 = segundoNumero.head

        // Suma de bits sin carry
        val sumaI = or(and(not(b1), b2), and(b1, not(b2)))

        // Nuevo carry
        val newCarry = or(and(b1, b2), and(carryIn, or(b1, b2)))

        // Suma total incluyendo el carry
        val sumaTotal = or(and(not(sumaI), carryIn), and(sumaI, not(carryIn)))

        sumaBits(primerNumero.tail, segundoNumero.tail, newCarry, sumaTotal :: acc)
      }
    }

    input => {
      val primerNumero = input.take(n)
      val segundoNumero = input.drop(n)
      sumaBits(primerNumero, segundoNumero, 0, Nil)
    }
  }
}

