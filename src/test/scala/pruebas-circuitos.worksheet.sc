import Circuitos._

val chipNot = crearChipUnario((x: Int) => 1 - x)
chipNot(List(0)) // List(1)
chipNot(List(1)) // List(0)

val chipAnd = crearChipBinario((x: Int, y: Int) => (x * y))
chipAnd(List(1,0)) // List(0)
chipAnd(List(1,1)) // List(1)

val chipOr = crearChipBinario((x: Int, y: Int) => x + y - (x * y))
chipOr(List(1,1)) // List(1)
chipOr(List(1,0)) // List(1)

halfAdder(List(0,0)) // List(0, 0)
halfAdder(List(0,1)) // List(0, 1)
halfAdder(List(1,0)) // List(0, 1)
halfAdder(List(1,1)) // List(1, 0)

fullAdder(List(0,0,0)) // List(0,0)
fullAdder(List(1,1,1)) // List(1,1)
fullAdder(List(0,1,1)) // List(1,0)
fullAdder(List(0,1,0)) // List(0,1)
fullAdder(List(1,1,0)) // List(1,0)
