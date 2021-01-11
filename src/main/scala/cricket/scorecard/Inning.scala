package cricket.scorecard

import cricket.scorecard.models.{Ball, Out, Wicket}
import cricket.scorecard.repository.PlayerRepository

case class Inning(scoreCard: ScoreCard, totalOvers: Int, team: PlayerRepository, endOfInning: Boolean = false) {
  def nextBall(ball: Ball): Inning = {
    val updatedScoreCard = scoreCard.updateScoreCard(ball)

    val isEndOfInning = updatedScoreCard.over.number > totalOvers

    if (ball == Wicket) {
      if (team.nonEmpty) {
        val nextPlayer = team.next
        val outPlayer = if (updatedScoreCard.onStrike.state == Out) updatedScoreCard.onStrike else updatedScoreCard.offStrike
        val updatedTeam = team.wicketOf(outPlayer)

        copy(scoreCard = updatedScoreCard.updatePlayer(nextPlayer), team = updatedTeam.pop(), endOfInning = isEndOfInning)
      } else {
        copy(scoreCard = updatedScoreCard, endOfInning = true)
      }
    } else
      copy(scoreCard = updatedScoreCard, endOfInning = isEndOfInning)
  }

  def hasFinishedAnOver: Boolean = scoreCard.over.number > 1 && scoreCard.over.balls.isEmpty

  def printStats(): Unit = {
    println(s"ScoreCard: ")
    val players = scoreCard.onStrike :: scoreCard.offStrike :: team.outPlayers
    val playerStats = players.map(_.statsString()).mkString("\n")
    println(playerStats)
    println(s"Score: ${scoreCard.totalScore}/${scoreCard.wickets}")
    println(s"Overs: ${scoreCard.over.number - 1}.${scoreCard.over.validCount}")
  }

}
