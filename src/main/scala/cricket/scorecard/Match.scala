package cricket.scorecard

import cricket.scorecard.models.Ball

case class Match(inning1: Inning, inning2: Inning, isSecondInning: Boolean = false, hasEnded: Boolean = false) {
  def result(): String = {
    val team1Score = inning1.scoreCard.totalScore
    val team2Score = inning2.scoreCard.totalScore
    if (team1Score > team2Score) {
      s"Team 1 won by ${team1Score - team2Score} run(s)"
    } else if (team1Score < team2Score) {
      s"Team 2 won by ${team2Score - team1Score} run(s)"
    } else {
      "Tie"
    }
  }


  def nextBall(ball: Ball): Match = {
    if (isSecondInning) {
      val updatedInning = inning2.nextBall(ball)
      val teamMadeMoreScore = updatedInning.scoreCard.totalScore > inning1.scoreCard.totalScore

      if(updatedInning.hasFinishedAnOver || updatedInning.endOfInning || teamMadeMoreScore) {
        updatedInning.printStats()
      }

      if (updatedInning.endOfInning || teamMadeMoreScore) copy(inning2 = updatedInning, hasEnded = true)
      else copy(inning2 = updatedInning)
    } else {
      val updatedInning = inning1.nextBall(ball)

      if(updatedInning.hasFinishedAnOver || updatedInning.endOfInning) {
        updatedInning.printStats()
      }

      if (updatedInning.endOfInning) copy(inning1 = updatedInning, isSecondInning = true)
      else copy(inning1 = updatedInning)
    }
  }
}
