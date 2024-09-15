package object recursion {

  /**
   * Ejercicio 1.1.1
   * Máximo común divisor a partir del teorema fundamental del álgebra
   */

  def mcdTFA(ln: List[Int], lm: List[Int], primos: List[Int]) : Int ={
   if (primos.isEmpty) 1
    else{
      val minExp = Math.min(ln.head, lm.head)
      val mcd = Math.pow(primos.head, minExp).toInt
      mcd * mcdTFA(ln.tail, lm.tail, primos.tail)
    }
  }


  /**
   * Ejercicio 1.1.2
   * Máximo común divisor a partir del teorema de Euclides con coeficientes de Bézout
   */
  def mcdEBez(n: Int, m: Int): (Int, Int, Int) = {
    if (m == 0) (n, 1, 0)
    else {
      val (d, x1, y1) = mcdEBez(m, n % m)
      (d, y1, x1 - (n / m) * y1)
    }
  }

  /**
   * Ejercicio 1.2.2
   * Fibonacci iterativo
   */
  def fibonacciI(n: Int): Int = {
    def fibIter(a: Int, b: Int, n: Int): Int = {
      if (n == 0) b
      else fibIter(b, a + b, n - 1)
    }

    fibIter(0, 1, n)
  }
}

  /**
   * Ejercicio 1.2.1
   * Fibonacci recursión de árbol
   */

  def fibonacciA(n: Int) : Int = {
    if (n == 0 || n == 1 ) 1
    else fibonacciA(n-1)+
      fibonacciA(n-2)
  }