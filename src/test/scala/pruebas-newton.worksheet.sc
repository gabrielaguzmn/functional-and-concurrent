import Newton._

val expr1 = Logaritmo(Suma(Atomo('x'),Expo(Numero(2.0), Atomo('x'))))
val expr2 = Expo(Numero(1), Prod(Atomo('x'),Logaritmo(Atomo('x'))))
val expr3 = Resta(Atomo('x'),Div(Numero(1.0),Expo(Atomo('x'),Numero(-3.0))))
val expr4 = Div(expr1, Logaritmo(expr2))
val expr5 = Prod(Expo(Atomo('x'),Atomo('x')),Logaritmo(Atomo('x')))

// Pruebas mostrar

// Pruebas derivar

// Pruebas evaluar

// Pruebas limpiar

// Pruebas RaizNewton

def buenaAprox (f: Expr , a: Atomo , d: Double ): Boolean = {
        evaluar(f, a, d) < 0.001
    }


