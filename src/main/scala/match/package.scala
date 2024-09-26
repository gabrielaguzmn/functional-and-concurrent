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

    def derivar(f: Expr, a: Atomo): Expr{
        
    }
  
    /**
      * Ejercicio 1.3
      * Evaluando expresiones
      */

    def evaluar(f: Expr, a: Atomo, v: Double): Double{

    }

    /**
      * Ejercicio 1.4
      * Limpiando expresiones
      */

    def limpiar(e: Expr): Expr = e match {
        case Suma(Numero(0), e2) => limpiar(e2)
        case Suma(e1, Numero(0)) => limpiar(e1)
        case Prod(Numero(1), e2) => limpiar(e2)
        case Prod(e1, Numero(1)) => limpiar(e1)
        case Prod(Numero(0), _) => Numero(0)
        case Prod(_, Numero(0)) => Numero(0)
        case Resta(e1, Numero(0)) => limpiar(e1)
        case Div(e1, Numero(1)) => limpiar(e1)
        case Expo(e1, Numero(1)) => limpiar(e1)
        case Suma(e1, e2) => Suma(limpiar(e1), limpiar(e2))
        case Prod(e1, e2) => Prod(limpiar(e1), limpiar(e2))
        case Resta(e1, e2) => Resta(limpiar(e1), limpiar(e2))
        case Div(e1, e2) => Div(limpiar(e1), limpiar(e2))
        case Expo(e1, e2) => Expo(limpiar(e1), limpiar(e2))
        case Logaritmo(e1) => Logaritmo(limpiar(e1))
        case _ => e
    }


    /**
      * Ejercicio 1.5
      * Hallando raices
      */

    def raizNewton(f: Expr, a: Atomo, x0: Double, ba:(Expr, Atomo, Double)=> Boolean): Double{

    }
}
