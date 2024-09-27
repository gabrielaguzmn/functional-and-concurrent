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

    def mostrar(e: Expr): String {

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

    def limpiar(f: Expr): Expr{

    }

    /**
      * Ejercicio 1.5
      * Hallando raices
      */   
    
    def raizNewton(f: Expr, a: Atomo, x0: Double, ba:(Expr, Atomo, Double)=> Boolean): Double = {
        if (ba(f,a,x0)) x0 else raizNewton(f,a,x0 - evaluar(f,a,x0)/evaluar(derivar(f,a),a,x0),ba) 
    }
}
