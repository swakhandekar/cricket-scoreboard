package cricket.scorecard

import cricket.scorecard.models.{Ball, Out, Wicket}
import cricket.scorecard.repository.PlayerRepository

case class Inning(scoreCard: ScoreCard, team: PlayerRepository, endOfInning: Boolean = false) {
  def nextBall(ball: Ball): Inning = {
    val updatedScoreCard = scoreCard.updateScoreCard(ball)

    if(ball == Wicket) {
      if(team.nonEmpty) {
        val nextPlayer = team.next
        val outPlayer = if(updatedScoreCard.onStrike.state == Out) updatedScoreCard.onStrike else updatedScoreCard.offStrike
        val updatedTeam = team.wicketOf(outPlayer)

        copy(scoreCard = updatedScoreCard.updatePlayer(nextPlayer), team = updatedTeam.pop())
      } else {
        copy(endOfInning = true)
      }
    } else
      copy(scoreCard = updatedScoreCard)
  }

}
