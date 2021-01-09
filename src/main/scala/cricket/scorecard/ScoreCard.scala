package cricket.scorecard

import cricket.scorecard.models.{Ball, Over, Player, Runs, Wicket}

case class ScoreCard(
                      totalScore: Int,
                      numPlayers: Int, players: List[Player],
                      onStrike: Player, offStrike: Player,
                      over: Over = Over.empty(1), wickets: Int = 0) {

  def nextBall(score: Ball): ScoreCard = {
    val updatedOver = over.copy(balls = score :: over.balls)
    val shouldRotate = shouldRotateStrike(score, updatedOver)

    copy(
      totalScore = totalScore + score.runs,
      over = if(updatedOver.isFinished) Over.empty(over.number + 1) else updatedOver,
      wickets = if(score == Wicket) wickets + 1 else wickets,
      onStrike = if(shouldRotate) offStrike else onStrike,
      offStrike = if(shouldRotate) onStrike else offStrike
    )
  }

  private def shouldRotateStrike(score: Ball, over: Over): Boolean = score match {
    case Runs(1) | Runs(3) => if(!over.isFinished) true else false
    case Runs(r) => over.isFinished
    case _ => false
  }
}
