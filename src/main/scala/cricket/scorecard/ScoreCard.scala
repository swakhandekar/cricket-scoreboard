package cricket.scorecard

import cricket.scorecard.models.{Ball, Over}

case class ScoreCard(totalScore: Int, over: Over = Over.empty(1)) {
  def nextBall(score: Ball): ScoreCard = {
    val updatedOver = over.copy(balls = score :: over.balls)

    copy(
      totalScore = totalScore + score.runs,
      over = if(updatedOver.isFinished) Over.empty(over.number + 1) else updatedOver
    )
  }
}
