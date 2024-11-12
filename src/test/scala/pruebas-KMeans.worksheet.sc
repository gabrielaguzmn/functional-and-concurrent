import Benchmark._
import kmedianas2D._

// Pruebas 1 
// Diferentes clusters y 512 puntos

// Generacion de puntos

val puntos1 = generarPuntos(2, 512).toSeq
val puntos2 = generarPuntos(8, 512).toSeq
val puntos3 = generarPuntos(16, 512).toSeq
val puntos4 = generarPuntos(64, 512).toSeq
val puntos5 = generarPuntos(128, 512).toSeq
val puntos6 = generarPuntos(256, 512).toSeq

// Calculo de tiempos

tiemposKmedianas(puntos1, 2, 0.001)
tiemposKmedianas(puntos2, 8, 0.001)
tiemposKmedianas(puntos3, 16, 0.001)
tiemposKmedianas(puntos4, 64, 0.001)
tiemposKmedianas(puntos5, 128, 0.001)
tiemposKmedianas(puntos6, 256, 0.001)

// Graficacion de la version secuencial

probarKmedianasSeq(puntos1, 2, 0.001)
probarKmedianasSeq(puntos2, 8, 0.001)
probarKmedianasSeq(puntos3, 16, 0.001)
probarKmedianasSeq(puntos4, 64, 0.001)
probarKmedianasSeq(puntos5, 128, 0.001)
probarKmedianasSeq(puntos6, 256, 0.001)

// Graficacion de la version paralela

probarKmedianasPar(puntos1, 2, 0.001)
probarKmedianasPar(puntos2, 8, 0.001)
probarKmedianasPar(puntos3, 16, 0.001)
probarKmedianasPar(puntos4, 64, 0.001)
probarKmedianasPar(puntos5, 128, 0.001)
probarKmedianasPar(puntos6, 256, 0.001)

// Pruebas 2 
// Diferentes clusters y 65536 puntos

// Generacion de puntos

val puntos7 = generarPuntos(2, 65536).toSeq
val puntos8 = generarPuntos(8, 65536).toSeq
val puntos9 = generarPuntos(16, 65536).toSeq
val puntos10 = generarPuntos(64, 65536).toSeq
val puntos11 = generarPuntos(128, 65536).toSeq
val puntos12 = generarPuntos(256, 65536).toSeq

// Calculo de tiempos

tiemposKmedianas(puntos7, 2, 0.01)
tiemposKmedianas(puntos8, 8, 0.01)
tiemposKmedianas(puntos9, 16, 0.01)
tiemposKmedianas(puntos10, 64, 0.01)
tiemposKmedianas(puntos11, 128, 0.01)
tiemposKmedianas(puntos12, 256, 0.01)

// Graficacion de la version secuencial

probarKmedianasSeq(puntos7, 2, 0.01)
probarKmedianasSeq(puntos8, 8, 0.01)
probarKmedianasSeq(puntos9, 16, 0.01)
probarKmedianasSeq(puntos10, 64, 0.01)
probarKmedianasSeq(puntos11, 128, 0.01)
probarKmedianasSeq(puntos12, 256, 0.01)

// Graficacion de la version paralela

probarKmedianasPar(puntos7, 2, 0.01)
probarKmedianasPar(puntos8, 8, 0.01)
probarKmedianasPar(puntos9, 16, 0.01)
probarKmedianasPar(puntos10, 64, 0.01)
probarKmedianasPar(puntos11, 128, 0.01)
probarKmedianasPar(puntos12, 256, 0.01)