import MatchingProblem._

val pilotPrefs1 = Vector(
  Vector(1, 2, 3),
  Vector(2, 1, 3),
  Vector(3, 2, 1)
)

val navigPrefs1 = Vector(
  Vector(1, 2, 3),
  Vector(2, 1, 3),
  Vector(3, 2, 1)
)

val pilotPrefs2 = Vector(
  Vector(1, 2, 3),
  Vector(1, 2, 3),
  Vector(1, 2, 3)
)

val navigPrefs2 = Vector(
  Vector(1, 2, 3),
  Vector(1, 2, 3),
  Vector(1, 2, 3)
)

val pilotPrefs3 = Vector(
  Vector(3, 2, 1),
  Vector(1, 3, 2),
  Vector(2, 1, 3)
)

val navigPrefs3 = Vector(
  Vector(2, 3, 1),
  Vector(1, 2, 3),
  Vector(3, 1, 2)
)

val pilotPrefs4 = Vector(
  Vector(1, 2, 3),
  Vector(3, 1, 2),
  Vector(2, 3, 1)
)

val navigPrefs4 = Vector(
  Vector(2, 1, 3),
  Vector(1, 3, 2),
  Vector(3, 2, 1)
)

val pilotPrefs5 = Vector(
  Vector(1, 3, 5),
  Vector(4, 2, 1),
  Vector(3, 1, 4)
)

val navigPrefs5 = Vector(
  Vector(5, 3, 1),
  Vector(4, 1, 3),
  Vector(3, 4, 2)
)

matchByElement(2,5) // List((2,1), (2,2), (2,3), (2,4), (2,5))
matchByElement(3,5) // List((3,1), (3,2), (3,3), (3,4), (3,5))
matchByElement(1,1) // List((1,1))
matchByElement(2,3) // List((2,1), (2,2), (2,3))
matchByElement(4,4) // List((4,1), (4,2), (4,3), (4,4))

matchsByElements(5) // List(List((1,1), (1,2), (1,3), (1,4), (1,5)), List((2,1), (2,2), (2,3), (2,4), (2,5)), ...)
matchsByElements(2) // List(List((1,1), (1,2)), List((2,1), (2,2)))
matchsByElements(1) // List(List((1,1)))
matchsByElements(3) // List(List((1,1), (1,2), (1,3)), List((2,1), (2,2), (2,3)), List((3,1), (3,2), (3,3)))
matchsByElements(4) // List(List((1,1), (1,2), (1,3), (1,4)), List((2,1), (2,2), (2,3), (2,4)), ...)

possibleMatchings(1) // List(List((1,1)))
possibleMatchings(2) // List(List((1,1), (2,1)), List((1,1), (2,2)), List((1,2), (2,1)), List((1,2), (2,2)))
possibleMatchings(3) // List(List((1,1), (2,1), (3,1)), List((1,1), (2,1), (3,2)), List((1,1), (2,1), (3,3)), ...)
possibleMatchings(5) // List(List((1,1), (2,1), (3,1), (4,1), (5,1)), List((1,1), (2,1), (3,1), (4,1), (5,2)), ...)
possibleMatchings(4) // List(List((1,1), (2,1), (3,1), (4,1)), List((1,1), (2,1), (3,1), (4,2)), ...)

matchings(1) // List(List((1,1)))
matchings(2) // List(List((1,1), (2,2)), List((1,2), (2,1)))
matchings(3) // List(List((1,1), (2,2), (3,3)), List((1,1), (2,3), (3,2)), List((1,2), (2,1), (3,3)), ...)
matchings(4) // List(List((1,1), (2,2), (3,3), (4,4)), List((1,1), (2,2), (3,4), (4,3)), ...)
matchings(5) // List(List((1,1), (2,2), (3,3), (4,4), (5,5)), List((1,1), (2,2), (3,3), (4,5), (5,4)), ...)

weightedMatchings(3, pilotPrefs1, navigPrefs1) // List((List((1,1), (2,2), (3,3)),3), (List((1,1), (2,3), (3,2)),13), ...) 
weightedMatchings(3, pilotPrefs2, navigPrefs2) // List((List((1,1), (2,2), (3,3)),14), (List((1,1), (2,3), (3,2)),13), ...)
weightedMatchings(3, pilotPrefs3, navigPrefs3) // List((List((1,1), (2,2), (3,3)),18), (List((1,1), (2,3), (3,2)),11), ...)
weightedMatchings(3, pilotPrefs4, navigPrefs4) // List((List((1,1), (2,2), (3,3)),6), (List((1,1), (2,3), (3,2)),12), ...)
weightedMatchings(3, pilotPrefs5, navigPrefs5) // List((List((1,1), (2,2), (3,3)),15), (List((1,1), (2,3), (3,2)),12), ...)

bestMatching(3, pilotPrefs1, navigPrefs1) // (List((1,2), (2,3), (3,1)),19)
bestMatching(3, pilotPrefs2, navigPrefs2) // (List((1,1), (2,2), (3,3)),14)
bestMatching(3, pilotPrefs3, navigPrefs3) // (List((1,1), (2,2), (3,3)),18)
bestMatching(3, pilotPrefs4, navigPrefs4) // (List((1,3), (2,1), (3,2)),18)
bestMatching(3, pilotPrefs5, navigPrefs5) // (List((1,2), (2,1), (3,3)),32)