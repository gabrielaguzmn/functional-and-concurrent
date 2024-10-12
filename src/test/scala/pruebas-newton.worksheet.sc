import Newton._

val expr1 = Resta(Logaritmo(Atomo('x')), Numero(1.0))
val expr2 = Prod(Atomo('x'), Logaritmo(Atomo('x')))
val expr3 = Suma(Resta(Expo(Atomo('x'),Numero(2.0)),Prod(Numero(4.0), Atomo('x'))),Numero(4.0))
val expr4 = Suma(expr2, Logaritmo(expr2))
val expr5 = Resta(Expo(Atomo('x'), Numero(5.0)), Prod(Numero(4.0),Atomo('x')))

// Pruebas mostrar

mostrar(Suma(Atomo('x'), Numero(2))) // (x + 2.0)
mostrar(Prod(Atomo('x'), Atomo('x'))) // (x * x)
mostrar(Expo(Atomo('x'), Numero(3))) // (x ^ 3.0)
mostrar(Logaritmo(Atomo('x'))) // lg(x)
mostrar(Suma(Prod(Numero(5), Atomo('y')), Logaritmo(Atomo('x')))) //((5.0 * y) + lg(x))
mostrar(expr1) // (lg(x) - 1.0)
mostrar(expr2) // (x * lg(x))
mostrar(expr3) // (((x ^ 2.0) - (4.0 * x)) + 4.0)
mostrar(expr4) // ((x * lg(x)) + lg((x * lg(x))))
mostrar(expr5) // ((x ^ 5.0) - (4.0 * x))

// Pruebas derivar

mostrar((derivar(expr1, Atomo('x')))) // ((1.0 / x) - 0.0)
mostrar((derivar(expr2, Atomo('x')))) // ((1.0 * lg(x)) + (x * (1.0 / x)))
mostrar((derivar(expr3, Atomo('x')))) // ((((x ^ 2.0) * (((1.0 * 2.0) / x) + (0.0 * lg(x)))) - ((0.0 * x) + (4.0 * 1.0))) + 0.0)
mostrar((derivar(expr4, Atomo('x')))) // (((1.0 * lg(x)) + (x * (1.0 / x))) + (((1.0 * lg(x)) + (x * (1.0 / x))) / (x * lg(x))))
mostrar((derivar(expr5, Atomo('x')))) // (((x ^ 5.0) * (((1.0 * 5.0) / x) + (0.0 * lg(x)))) - ((0.0 * x) + (4.0 * 1.0)))

// Pruebas evaluar

evaluar(expr1, Atomo('x'), 1.0) // -1.0
evaluar(expr2, Atomo('x'), 2.7) // 2.6817797871277653
evaluar(expr3, Atomo('x'), 1.0) // 1.0
evaluar(expr4, Atomo('x'), 6.0) // 13.125514365379045
evaluar(expr5, Atomo('x'), 2.0) // 24.0

// Pruebas limpiar

limpiar(derivar(Suma(Atomo('k'),Prod(Numero(3.0),Atomo('x'))),Atomo('x'))) // Numero (3.0)
mostrar(limpiar(derivar(Suma(Atomo('k'),Prod(Numero (3.0) , Atomo('x'))), Atomo('x')))) // 3.0
limpiar(Suma(Numero(0), Atomo('x'))) // Atomo(x)
limpiar(Prod(Numero(1), Atomo('x'))) // Atomo (x)
limpiar(Prod(Numero(0), Atomo('x'))) // Numero(0.0)
limpiar(Expo(Atomo('x'), Numero(0))) // Numero(1.0)
limpiar(Suma(Numero(0), Prod(Numero(1), Atomo('x')))) // Atomo(x)
limpiar(Suma(Numero(0), Prod(Numero(3), Atomo('x')))) // Prod(Numero(3.0),Atomo(x))
limpiar(Suma(Numero(0), Numero(0))) // Numero(0.0)
limpiar(Suma(Numero(5), Numero(0))) // Numero(5.0)
limpiar(Prod(Suma(Numero(0), Atomo('x')), Numero(2))) // Prod(Atomo(x), Numero(2.0))
limpiar(Resta(Numero(0), Numero(5))) // Prod(Numero(-1.0),Numero(-5.0))
limpiar(Resta(Numero(5), Numero(0))) // Numero(5.0)
limpiar(Div(Numero(0),Numero(2))) // Numero(0.0)
limpiar(Logaritmo(Numero(1))) // Numero(0.0)
limpiar(Resta(Numero(0),Logaritmo(Atomo('x')))) // Prod(Numero(-1.0),Logaritmo(Atomo(x)))
limpiar(Resta(Prod(Numero(0), Atomo('x')), Prod(Numero(2.0), Atomo('y')))) // Prod(Numero(2.0),Atomo(y))


// Pruebas RaizNewton

def buenaAprox (f: Expr , a: Atomo , d: Double ): Boolean = {
        evaluar(f, a, d) < 0.001
    }

raizNewton(expr1, Atomo('x'), 3.0, buenaAprox) // 2.704163133995671
raizNewton(expr2, Atomo('x'), 2.0, buenaAprox) // 1.0000774890317474
raizNewton(expr3, Atomo('x'), 2.5, buenaAprox) // 2.03125
raizNewton(expr4, Atomo('x'), 4.0, buenaAprox) // 1.4231252002993577
raizNewton(expr5, Atomo('x'), 2.0, buenaAprox) // 1.4142136175485605