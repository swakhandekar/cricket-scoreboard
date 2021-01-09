package cricket.scorecard

import cricket.scorecard.models.Ball

case class ScoreCard(totalScore: Int) {
  def nextBall(score: Ball): ScoreCard = copy(totalScore + score.runs)
}
