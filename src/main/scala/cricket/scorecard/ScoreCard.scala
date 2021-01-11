package cricket.scorecard

import cricket.scorecard.models._

case class ScoreCard(
                      totalScore: Int,
                      onStrike: Player, offStrike: Player,
                      over: Over = Over.empty(1), wickets: Int = 0, extras: Int = 0) {
  def updateOutPlayer(nextPlayer: Player): ScoreCard = {
    if (onStrike.state == Out)
      copy(onStrike = nextPlayer)
    else copy(offStrike = nextPlayer)
  }

  def updateScoreCard(score: Ball): ScoreCard = {
    val updatedOver = over.copy(balls = score :: over.balls)
    val shouldRotate = shouldRotateStrike(score, updatedOver)
    val updatedOnStrike = onStrike.plays(score)
    val updatedExtras = updateExtras(score)

    copy(
      totalScore = totalScore + score.runs,
      over = if (updatedOver.isFinished) Over.empty(over.number + 1) else updatedOver,
      wickets = if (score == Wicket) wickets + 1 else wickets,
      onStrike = if (shouldRotate) offStrike else updatedOnStrike,
      offStrike = if (shouldRotate) updatedOnStrike else offStrike,
      extras = updatedExtras
    )
  }

  private def shouldRotateStrike(score: Ball, over: Over): Boolean = score.valid match {
    case 1 | 3 => if (!over.isFinished) true else false
    case _ => over.isFinished
  }

  private def updateExtras(ball: Ball): Int = {
    val extraOnThisBall = ball match {
      case w: WideBall => w.extra
      case n: NoBall => n.extra
      case _ => 0
    }

    extras + extraOnThisBall
  }
}
