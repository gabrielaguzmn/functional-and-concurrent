import Newton._

val expr1 = Logaritmo(Suma(Atomo('x'),Expo(Numero(2.0), Atomo('x'))))
val expr2 = Expo(Numero(1), Prod(Atomo('x'),Logaritmo(Atomo('x'))))
val expr3 = Resta(Atomo('x'),Div(Numero(1.0),Expo(Atomo('x'),Numero(3.0))))
val expr4 = Div(expr1, Logaritmo(expr2))
val expr5 = Prod(Expo(Atomo('x'),Atomo('x')),Logaritmo(Atomo('x')))
val expr6=Expo (Atomo('x') , Numero(3))


// Pruebas mostrar
mostrar(Suma(Atomo('x'), Numero(2))) // (x + 2.0)
mostrar(Prod(Atomo('x'), Atomo('x'))) // (x * x)
mostrar(Expo(Atomo('x'), Numero(3))) // (x ^ 3.0)
mostrar(Logaritmo(Atomo('x'))) // lg(x)
mostrar(Suma(Prod(Numero(5), Atomo('y')), Logaritmo(Atomo('x')))) //((5.0 * y) + lg(x))
mostrar(expr1)
mostrar(expr2)
mostrar(expr3)
mostrar(expr4)
mostrar(expr5)
mostrar(derivar(expr6,Atomo('x')))


// Pruebas derivar


// Pruebas evaluar

// Pruebas limpiar
limpiar(derivar(Suma(Atomo('k'),Prod(Numero(3.0),Atomo('x'))),Atomo('x')))
mostrar(limpiar(derivar(Suma(Atomo('k'),Prod(Numero (3.0) , Atomo('x'))), Atomo('x'))))
limpiar(Suma(Numero(0), Atomo('x')))
limpiar(Prod(Numero(1), Atomo('x')))
limpiar(Prod(Numero(0), Atomo('x')))
limpiar(Expo(Atomo('x'), Numero(1)))
limpiar(Suma(Numero(0), Prod(Numero(1), Atomo('x'))))
limpiar(Suma(Numero(0), Prod(Numero(3), Atomo('x'))))
limpiar(Suma(Numero(0), Numero(0)))
limpiar(Suma(Numero(5), Numero(0)))
limpiar(Prod(Suma(Numero(0), Atomo('x')), Numero(2)))
// Pruebas RaizNewton

def buenaAprox (f: Expr , a: Atomo , d: Double ): Boolean = {
        evaluar(f, a, d) < 0.001
    }


