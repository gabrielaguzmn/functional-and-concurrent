package object MatchingProblem {

    type Match = (Int, Int)
    type Matching = List[ Match ]
    type Preferences = Vector[Vector[Int]]

    /**
   * Ejercicio 1.1
   * Emparejamientos posibles de un piloto
   */

    def matchByElement(i: Int, n: Int): List[(Int, Int)] = {
        for (j <- 1 to n) yield (i, j)
    }.toList

    /**
   * Ejercicio 1.2
   * Emparejamientos posibles de todos los pilotos
   */

    def matchsByElements(n: Int): List[List[(Int, Int)]] = {
        for (i <- 1 to n) yield matchByElement(i, n)
    }.toList

    /**
   * Ejercicio 1.3
   * Posibles emparejamientos
   */

    def possibleMatchings(n: Int): List[List[(Int, Int)]] = {
        val allMatches = matchsByElements(n)
        allMatches.foldRight(List(List.empty[(Int, Int)])) { (matches, acc) =>
            for {
                matchPair <- matches
                matchList <- acc
            } yield matchPair :: matchList
        }
    }

    /**
   * Ejercicio 1.4
   * Calculando los emparejamientos validos 
   */    

    def isValidMatching(matching: Matching, n: Int): Boolean = {
        val copilots = for ((_, copilot) <- matching) yield copilot
        copilots.toSet == (1 to n).toSet
    }

    def matchings (n: Int) : List[Matching] = {
        val allMatchings = possibleMatchings(n)
        for{
            matching <- allMatchings 
            if (isValidMatching(matching, n)) 
        } yield matching
    }

    /**
   * Ejercicio 1.5
   * Calculando los pesos de los emparejamientos validos
   */

    def weightedMatchings (n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): List[(Matching, Int) ] = {
        val validMatchings = matchings(n)
        for{
            matching <- validMatchings
            weights = (for {
                tuple <- matching
                pilot = tuple._1 - 1
                copilot = tuple._2 - 1
                pilotPreference = pilotPrefs(pilot)(copilot)
                copilotPreference = navigPrefs(copilot)(pilot)
                weight = pilotPreference * copilotPreference
            } yield weight).sum
        } yield (matching, weights)
    }

    /**
   * Ejercicio 1.6
   * Calculando el mejor emparejamiento 
   */

    def bestMatching (n: Int, pilotPrefs: Preferences, navigPrefs: Preferences) : (Matching, Int) = {
        val matchingsWithWeight = weightedMatchings(n, pilotPrefs, navigPrefs)
        val bestWeight = (for ((_, weight) <- matchingsWithWeight) yield weight).max
        val bestMatching = for {
            matching <- matchingsWithWeight
            if matching._2 == bestWeight 
        } yield matching
        bestMatching.head
    }
}