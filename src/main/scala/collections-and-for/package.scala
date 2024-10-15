package object MatchingProblem {

    type Match = (Int, Int)
    type Matching = List[ Match ]
    type Preferences = Vector[Vector[Int]]

    def matchByElement(i: Int, n: Int): List[(Int, Int)] = {
        (1 to n).toList.map(j => (i, j))
    }

    def matchsByElements(n: Int): List[List[(Int, Int)]] = {
        (1 to n).toList.map(i => matchByElement(i, n))
    }

    def possibleMatchings(n: Int): List[List[(Int, Int)]] = {
        val allMatches = matchsByElements(n)
        allMatches.foldRight(List(List.empty[(Int, Int)])) {
            (matches, acc) =>
                for {
                    matchPair <- matches
                    matchList <- acc
                } yield matchPair :: matchList
        }
    }

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

    /*def weightedMatchings (n: Int, pilotPrefs: Preferences, navigPrefs: Preferences) : List[(Matching, Int) ] = {

    }

    def bestMatching (n: Int, pilotPrefs: Preferences, navigPrefs: Preferences) : (Matching, Int) = {
        
    }
*/

}