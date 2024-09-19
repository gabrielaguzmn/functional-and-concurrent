import Circuitos._


val chipAnd = crearChipBinario((x: Int, y: Int) => (x * y))
chipAnd(List(1,0))
chipAnd(List(1,1))

val chipOr = crearChipBinario((x: Int, y: Int) => x + y - (x * y))
chipOr(List(1,1))
chipOr(List(1,0))

ha(List(0,0))
ha(List(0,1))
halfAdder(List(1,0))
halfAdder(List(1,1))

fa ( List (1 ,1 ,0))
fa ( List (1 ,1 ,1))
fa ( List (0 ,1 ,0))
fa ( List (0 ,1 ,1))

val add_1 = adder(1)
add_1(List(1) ++ List(1))

adder(1) (List(1) ++ List (1))
adder(1) (List(0) ++ List (0))
adder(1) (List(1) ++ List (0))
adder(1) (List(0) ++ List (1))

adder(2) (List(1,0) ++ List (0,1))
adder(2) (List(1,1) ++ List (0,1))

adder(3) (List(1,0,1) ++ List (0,0,0))
adder(3) (List(1,0,1) ++ List (1,0,1))

val ad4= adder(4)
ad4(List(1,0,1,1) ++ List(1,0,1,0))