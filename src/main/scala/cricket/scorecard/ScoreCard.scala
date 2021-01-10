package cricket.scorecard

import cricket.scorecard.models.{Ball, Over, Player, Runs, Wicket}
import cricket.scorecard.repository.PlayerRepository

case class ScoreCard(
                      totalScore: Int,
                      playerRepository: PlayerRepository,
                      onStrike: Player, offStrike: Player,
                      over: Over = Over.empty(1), wickets: Int = 0) {

  def nextBall(score: Ball): ScoreCard = {
    val updatedOver = over.copy(balls = score :: over.balls)
    val shouldRotate = shouldRotateStrike(score, updatedOver)

    val (updatedOnStrike, updatedOffStrike) = updatedOnFieldPlayers(score, shouldRotate)

    copy(
      totalScore = totalScore + score.runs,
      over = if(updatedOver.isFinished) Over.empty(over.number + 1) else updatedOver,
      wickets = if(score == Wicket) wickets + 1 else wickets,
      onStrike = updatedOnStrike,
      offStrike = updatedOffStrike,
      playerRepository = updatePlayerRepository(score)
    )
  }

  private def updatePlayerRepository(score: Ball) = {
    if (score == Wicket && playerRepository.nonEmpty) playerRepository.wicketOf(onStrike).pop() else playerRepository
  }

  private def updatedOnFieldPlayers(score: Ball, shouldRotate: Boolean) = {
    val onStrikePlays = onStrike.plays(score)
    if (score == Wicket && playerRepository.nonEmpty) (playerRepository.next, offStrike)
    else if (shouldRotate) (offStrike, onStrikePlays)
    else (onStrikePlays, offStrike)
  }

  private def shouldRotateStrike(score: Ball, over: Over): Boolean = score match {
    case Runs(1) | Runs(3) => if(!over.isFinished) true else false
    case Runs(r) => over.isFinished
    case _ => false
  }
}
