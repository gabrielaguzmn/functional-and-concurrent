import Circuitos._


val chipAnd = crearChipBinario((x: Int, y: Int) => (x * y))
chipAnd(List(1,0))
chipAnd(List(1,1))

val chipOr = crearChipBinario((x: Int, y: Int) => x + y - (x * y))
chipOr(List(1,1))
chipOr(List(1,0))

halfAdder(List(0,0))
halfAdder(List(0,1))
halfAdder(List(1,0))
halfAdder(List(1,1))