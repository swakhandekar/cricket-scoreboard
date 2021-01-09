package cricket.scorecard

import cricket.scorecard.models.{Ball, Over, Wicket}

case class ScoreCard(totalScore: Int, over: Over = Over.empty(1), wickets: Int = 0) {
  def nextBall(score: Ball): ScoreCard = {
    val updatedOver = over.copy(balls = score :: over.balls)

    copy(
      totalScore = totalScore + score.runs,
      over = if(updatedOver.isFinished) Over.empty(over.number + 1) else updatedOver,
      wickets = if(score == Wicket) wickets + 1 else wickets
    )
  }
}
