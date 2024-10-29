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

// Clasificar puntos
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

  def clasificarSeq(puntos: Seq[Punto], medianas: Seq[Punto]): Map[Punto, Seq[Punto]] = {
    puntos.groupBy(punto => hallarPuntoMasCercano(punto, medianas))
  }

  def actualizarSeq(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    for{
      mediana <- medianasViejas
      medianaActualizada = calculePromedioSeq(mediana, clasif.getOrElse(mediana, Seq()))
    } yield medianaActualizada
  }


  @tailrec
  def hayConvergenciaSeq(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto], index: Int = 0): Boolean = {
    if (index >= medianasViejas.length) true
    else if (medianasViejas(index).distanciaAlCuadrado(medianasNuevas(index)) >= eta) false
    else hayConvergenciaSeq(eta, medianasViejas, medianasNuevas, index + 1)
  }

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


  def actualizarPar(clasif: Map[Punto, Seq[Punto]], medianasViejas: Seq[Punto]): Seq[Punto] = {
    medianasViejas.par.map(mediana => calculePromedioPar(mediana, clasif.getOrElse(mediana, Seq()))).toList
  }

  def hayConvergenciaPar(eta: Double, medianasViejas: Seq[Punto], medianasNuevas: Seq[Punto]): Boolean = {
    // Implementacion pendiente
  }

  @tailrec
  final def kMedianasPar(puntos: Seq[Punto], medianas: Seq[Punto], eta: Double): Seq[Punto] = {
    val clasificacion = clasificarPar(10)(puntos, medianas)
    val medianasNuevas = actualizarPar(clasificacion, medianas)
    if (hayConvergenciaPar(eta, medianas, medianasNuevas)) medianasNuevas
    else kMedianasPar(puntos, medianasNuevas, eta)
  }
}
