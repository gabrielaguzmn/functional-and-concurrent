import recursion._

// Pruebas mcdTFA
println(mcdTFA(List(1, 1, 1), List(1, 1, 1), List(2, 3, 5))) // 30
println(mcdTFA(List(3, 0, 2), List(2, 1, 3), List(2, 3, 5))) // 100
println(mcdTFA(List(6, 3, 2), List(5, 3, 2), List(2, 2, 3))) // 2304
println(mcdTFA(List(4, 0, 0), List(3, 0, 0), List(2, 3, 5))) // 8
println(mcdTFA(List(4, 2, 1, 3, 0), List(3, 1, 2, 3, 1), List(2, 3, 5, 7, 11))) // 41160
println(mcdTFA(List(2), List(1), List(7))) // 1

// Pruebas mcdEBez
println(mcdEBez(963, 657))  // (9, -15, 22)
println(mcdEBez(120, 500))  // (20, -4, 1)
println(mcdEBez(18, 18))    // (18, 0, 1)
println(mcdEBez(500, 120))  // (20, 1, -4)
println(mcdEBez(1, 3))    // (1, 1, 0)

// Pruebas fibonacciI
println(fibonacciI(5)) // 8
println(fibonacciI(10)) // 89
println(fibonacciI(20)) // 10946
println(fibonacciI(0))  // 1
println(fibonacciI(1))  // 1

// Pruebas fibonacciA
println(fibonacciA(0)) // 1
println(fibonacciA(5)) // 8
println(fibonacciA(10)) // 89
println(fibonacciA(20)) // 10946
println(fibonacciA(6)) // 13