package cricket.scorecard

import cricket.scorecard.models.{Ball, Over}

case class ScoreCard(totalScore: Int, over: Over = Over.empty(1)) {
  def nextBall(score: Ball): ScoreCard = {
    copy(
      totalScore = totalScore + score.runs,
      over = over.copy(balls = score :: over.balls)
    )
  }
}
