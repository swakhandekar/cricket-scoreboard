package cricket.scorecard

import cricket.scorecard.models._

object Input2 {
  val numOvers = 2
  val team1Players: List[String] = List("P1", "P2", "P3", "P4", "P5")
  val team2Players: List[String] = List("P6", "P7", "P8", "P9", "P10")

  val balls: List[Ball] = List(
    // inning 1
    Runs(1), Runs(1), Runs(1), Runs(1), Runs(1), Runs(2),
    Wicket, Runs(4), Runs(4), WideBall(), Wicket, Runs(1), Runs(6),

    // inning 2
    Runs(4), Runs(6), Wicket, Wicket, Runs(1), Runs(1),
    Runs(6), Runs(1), Wicket, Wicket
  )
}
