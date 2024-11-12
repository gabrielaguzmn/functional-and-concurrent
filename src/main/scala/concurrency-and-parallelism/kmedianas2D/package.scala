package object kmedianas2D {
  import scala.annotation.tailrec
  import scala.collection.{Map, Seq}
  import scala.collection.parallel.CollectionConverters._
  import scala.util.Random
  import common._

  class Punto(val x: Double, val y: Double) {
    private def cuadrado(v: Double): Double = v * v

    def distanciaAlCuadrado(that: Punto): Double =
      cuadrado(that.x - x) + cuadrado(that.y - y)

    private def round(v: Double): Double = (v * 100).toInt / 100.0

    override def toString: String = s"(${round(x)}, ${round(y)})"
  }

  def generarPuntos(k: Int, num: Int): Seq[Punto] = {
    val randx = new Random(1)
    val randy = new Random(3)
    (0 until num).map { i =>
      val x = ((i + 1) % k) * 1.0 / k + randx.nextDouble() * 0.5
      val y = ((i + 5) % k) * 1.0 / k + randy.nextDouble() * 0.5
      new Punto(x, y)
    }
  }

  def inicializarMedianas(k: Int, puntos: Seq[Punto]): Seq[Punto] = {
    val rand = new Random(7)
    (0 until k).map(_ => puntos(rand.nextInt(puntos.length)))
  }

  def hallarPuntoMasCercano(p: Punto, medianas: Seq[Punto]): Punto = {
    assert(medianas.nonEmpty)
    medianas.minBy(p.distanciaAlCuadrado)
  }

// Versiones secuenciales

  def calculePromedioSeq(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
    if (puntos.isEmpty) medianaVieja
    else {
      new Punto(
        puntos.map(_.x).sum / puntos.length,
        puntos.map(_.y).sum / puntos.length
      )
    }
  }

  /**
   * Ejercicio 1.1
   * Clasificar puntos, version secuencial
   */

  def clasificarSeq(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
    puntos.groupBy(punto => hallarPuntoMasCercano(punto, medianas))
  }

  /**
   * Ejercicio 2.1
   * Actualizar puntos, version secuencial
   */

  def actualizarSeq(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    for{
      mediana <- medianasViejas
      medianaActualizada = calculePromedioSeq(mediana, clasif.getOrElse(mediana, Seq()))
    } yield medianaActualizada
  }

  /**
   * Ejercicio 3.1
   * Verificar convergencia, version secuencial
   */

  @tailrec
  def hayConvergenciaSeq(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto], index: Int = 0): Boolean = {
    if (index >= medianasViejas.length) true
    else if (medianasViejas(index).distanciaAlCuadrado(medianasNuevas(index)) >= eta) false
    else hayConvergenciaSeq(eta, medianasViejas, medianasNuevas, index + 1)
  }

  /**
   * Ejercicio 4.1
   * Implementacion de algoritmo KMeans, version secuencial
   */

  @tailrec
  final def kMedianasSeq(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasification = clasificarSeq(puntos, medianas)
    val medianasActualizadas = actualizarSeq(clasification, medianas)
    if (hayConvergenciaSeq(eta, medianas, medianasActualizadas)) medianasActualizadas
    else kMedianasSeq(puntos, medianasActualizadas, eta)
  }

// Versiones paralelas

  def calculePromedioPar(medianaVieja: Punto, puntos: Seq[Punto]): Punto = {
    if (puntos.isEmpty) medianaVieja
    else {
      val puntosPar = puntos.par
      new Punto(
        puntosPar.map(_.x).sum / puntos.length,
        puntosPar.map(_.y).sum / puntos.length
      )
    }
  }

  /**
   * Ejercicio 1.2
   * Clasificar puntos, version paralela
   */

  def clasificarPar(umb: Int)(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
    if (puntos.length > umb){
      val (left, right) = puntos.splitAt(puntos.size / 2)
      val (leftMap, rightMap) = parallel(clasificarPar(umb)(left, medianas), clasificarPar(umb)(right, medianas))
      val clasification = (for {
        key <- leftMap.keySet ++ rightMap.keySet
      } yield {
      val leftValues = leftMap.getOrElse(key, Seq())
      val rightValues = rightMap.getOrElse(key, Seq())
      key -> (leftValues ++ rightValues)
      }).toMap
      clasification
    }
    else clasificarSeq(puntos, medianas)
  }

  /**
   * Ejercicio 2.2
   * Actualizar puntos, version paralela
   */

  def actualizarPar(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    medianasViejas.par.map(mediana => calculePromedioPar(mediana, clasif.getOrElse(mediana, Seq()))).toList
  }

  /**
   * Ejercicio 3.2
   * Verificar convergencia, version paralela
   */

  def converge(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]) : Boolean = {
    medianasViejas.zip(medianasNuevas).forall { case (vieja, nueva) =>
      vieja.distanciaAlCuadrado(nueva) <= eta
    }
  }

  def hayConvergenciaPar(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]): Boolean = {
    val (medianasViejas1, medianasViejas2) = medianasViejas.splitAt(medianasViejas.size / 2)
    val (medianasNuevas1, medianasNuevas2) = medianasNuevas.splitAt(medianasNuevas.size / 2)
    val (convergencia1, convergencia2) = parallel(
      converge(eta, medianasViejas1, medianasNuevas1),
      converge(eta, medianasViejas2, medianasNuevas2))
    convergencia1 && convergencia2
  }

  /**
   * Ejercicio 4.2
   * Implementacion de algoritmo KMeans, version paralela
   */

  @tailrec
  final def kMedianasPar(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasificacion = clasificarPar(10)(puntos, medianas)
    val medianasNuevas = actualizarPar(clasificacion, medianas)
    if (hayConvergenciaPar(eta, medianas, medianasNuevas)) medianasNuevas
    else kMedianasPar(puntos, medianasNuevas, eta)
  }
}