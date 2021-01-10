package cricket.scorecard

import cricket.scorecard.models.Ball

case class Match(inning1: Inning, inning2: Inning, isSecondInning: Boolean = false, hasEnded: Boolean = false) {
  def result(): String = {
    if (inning1.scoreCard.totalScore > inning2.scoreCard.totalScore) {
      "Team 1"
    } else if (inning1.scoreCard.totalScore < inning2.scoreCard.totalScore) {
      "Team 2"
    } else {
      "Tie"
    }
  }


  def nextBall(ball: Ball): Match = {
    if (isSecondInning) {
      val updatedInning = inning2.nextBall(ball)
      val teamMadeMoreScore = updatedInning.scoreCard.totalScore > inning1.scoreCard.totalScore

      if (updatedInning.endOfInning || teamMadeMoreScore) copy(inning2 = updatedInning, hasEnded = true)
      else copy(inning2 = updatedInning)
    } else {
      val updatedInning = inning1.nextBall(ball)

      if (updatedInning.endOfInning) copy(inning1 = updatedInning, isSecondInning = true)
      else copy(inning1 = updatedInning)
    }
  }
}
