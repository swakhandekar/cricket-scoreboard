package cricket.scorecard

import cricket.scorecard.models.{Ball, NoBall, Player, Runs, Wicket, WideBall}

object Input {
  val numOvers = 2
  val team1Players = List("A", "B", "C", "D", "E")
  val team2Players = List("P", "Q", "R", "S", "T")

  val balls: List[Ball] = List(
    // inning 1
    Runs(1), Runs(2), Runs(0), Runs(4), Wicket, WideBall(2), Runs(0),
    Runs(0), Wicket, Wicket, NoBall(), Runs(1), Runs(6), Runs(3),

    // inning 2
    Runs(3), Wicket, Runs(0), Runs(4), Wicket, WideBall(), Runs(0),
    Runs(0), Wicket, Wicket
  )
}
