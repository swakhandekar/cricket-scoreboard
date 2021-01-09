package cricket.scorecard

import cricket.scorecard.models.Score

case class ScoreCard(totalScore: Int) {
  def nextBall(score: Score): ScoreCard = copy(totalScore + 1)
}
