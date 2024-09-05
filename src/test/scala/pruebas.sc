import recursion._

// Pruebas mcdTFA
mcdTFA(List(3,1,1), List(2,0,3), List(2,3,5)) // 20
mcdTFA(List(6, 2, 4), List(3, 1, 5), List(1, 2, 3)) // 162
mcdTFA(List(9, 3, 6), List(3, 1, 2), List(2, 4, 8)) // 2048
mcdTFA(List(8, 4, 2), List(2, 1, 3), List(3, 6, 9)) // 4374
mcdTFA(List(5, 3, 2), List(1, 4, 6), List(3, 2, 1)) // 24

// Pruebas mcdEBez
println(mcdEBez(963, 657))  // (9, -15, 22)
println(mcdEBez(120, 500))  // (20, -4, 1)
println(mcdEBez(18, 18))    // (6, -1, 3)
println(mcdEBez(500, 120))  // (6, -7, 10)
println(mcdEBez(1, 3))    // (14, 3, -1)


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