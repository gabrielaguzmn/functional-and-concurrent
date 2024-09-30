import scala.math._

package object Newton {

    trait Expr
    case class Numero ( d : Double ) extends Expr
    case class Atomo ( x : Char ) extends Expr
    case class Suma( e1 : Expr , e2 : Expr ) extends Expr
    case class Prod ( e1 : Expr , e2 : Expr ) extends Expr
    case class Resta ( e1 : Expr , e2 : Expr ) extends Expr
    case class Div ( e1 : Expr , e2 : Expr ) extends Expr
    case class Expo ( e1 : Expr , e2 : Expr ) extends Expr
    case class Logaritmo ( e1 : Expr ) extends Expr

    /**
      * Ejercicio 1.1
      * Mostrando expresiones
      */

    def mostrar(e: Expr): String = e match {
        case Numero(d) => d.toString
        case Atomo(x) => x.toString
        case Suma(e1, e2) => s"(${mostrar(e1)} + ${mostrar(e2)})"
        case Prod(e1, e2) => s"(${mostrar(e1)} * ${mostrar(e2)})"
        case Resta(e1, e2) => s"(${mostrar(e1)} - ${mostrar(e2)})"
        case Div(e1, e2) => s"(${mostrar(e1)} / ${mostrar(e2)})"
        case Expo(e1, e2) => s"(${mostrar(e1)} ^ ${mostrar(e2)})"
        case Logaritmo(e1) => s"lg(${mostrar(e1)})"
    }

    /**
      * Ejercicio 1.2
      * Derivando expresiones
      */

    def derivar(f: Expr, a: Atomo): Expr = f match{
      case Numero(d) => Numero(0)
      case Atomo(x) => if (Atomo(x) == a) Numero(1) else Numero(0)
      case Suma(e1, e2) => Suma(derivar(e1, a),derivar(e2, a))
      case Resta(e1, e2) => Resta(derivar(e1, a), derivar(e2, a))
      case Prod(e1,e2) =>  Suma(Prod(derivar(e1,a),e2),Prod(e1,derivar(e2,a)))
      case Div(e1,e2) => Div(Resta(Prod(derivar(e1,a),e2),Prod(e1,derivar(e2,a))),Expo(e2,Numero(2)))
      case Expo(e1,e2) => Prod(Expo(e1,e2),Suma(Div(Prod(derivar(e1,a),e2),e1),Prod(derivar(e2, a),Logaritmo(e1))))
      case Logaritmo(e1) => Div(derivar(e1, a), e1)
    }
  
    /**
      * Ejercicio 1.3
      * Evaluando expresiones
      */

    def evaluar(f: Expr, a: Atomo, v: Double): Double = f match{
        case Numero(d) => d
        case Atomo(x) => v
        case Suma(e1,e2) => evaluar(e1,a,v) + evaluar(e2,a,v)
        case Resta(e1,e2) => evaluar(e1,a,v) - evaluar(e2,a,v)
        case Prod(e1,e2) => evaluar(e1,a,v) * evaluar(e2,a,v)
        case Div(e1,e2) => evaluar(e1,a,v) / evaluar(e2,a,v)
        case Expo(e1,e2) => pow(evaluar(e1,a,v), evaluar(e2,a,v))
        case Logaritmo(e1) => log(evaluar(e1,a,v))
    }

    /**
      * Ejercicio 1.4
      * Limpiando expresiones
      */
    def limpiar(e: Expr): Expr = e match {
        // Sumas con cero
        case Suma(Numero(0), e2) => limpiar(e2)
        case Suma(e1, Numero(0)) => limpiar(e1)
        case Suma(e1, e2) =>
            (limpiar(e1), limpiar(e2)) match {
                case (Numero(0), e2Limpio) => e2Limpio
                case (e1Limpio, Numero(0)) => e1Limpio
                case (e1Limpio, e2Limpio) => Suma(e1Limpio, e2Limpio)
            }

        // Productos con 1 o 0
        case Prod(Numero(1), e2) => limpiar(e2)
        case Prod(e1, Numero(1)) => limpiar(e1)
        case Prod(Numero(0), _) => Numero(0)
        case Prod(_, Numero(0)) => Numero(0)

        // Restas con 0
        case Resta(e1, Numero(0)) => limpiar(e1)

        // División por 1
        case Div(e1, Numero(1)) => limpiar(e1)

        // Exponenciación por 1
        case Expo(e1, Numero(1)) => limpiar(e1)

        // Recursión en otras expresiones
        case Prod(e1, e2) => Prod(limpiar(e1), limpiar(e2))
        case Resta(e1, e2) => Resta(limpiar(e1), limpiar(e2))
        case Div(e1, e2) => Div(limpiar(e1), limpiar(e2))
        case Expo(e1, e2) => Expo(limpiar(e1), limpiar(e2))
        case Logaritmo(e1) => Logaritmo(limpiar(e1))

        // Caso base: la expresión ya está limpia
        case _ => e
    }
    

    /**
      * Ejercicio 1.5
      * Hallando raices
      */   
    
    def raizNewton(f: Expr, a: Atomo, x0: Double, ba:(Expr, Atomo, Double)=> Boolean): Double = {
        val x1= x0 - evaluar(f,a,x0)/evaluar(derivar(f,a),a,x0)
        if (ba(f,a,x0)) x0 else raizNewton(f,a,x1,ba) 
    }
}
