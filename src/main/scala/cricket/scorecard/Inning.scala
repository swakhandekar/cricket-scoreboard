package cricket.scorecard

import cricket.scorecard.models.{Ball, Out, Wicket}
import cricket.scorecard.repository.{BowlersRepository, PlayerRepository}

case class Inning(scoreCard: ScoreCard, totalOvers: Int, team: PlayerRepository, bowlersRepo: BowlersRepository, endOfInning: Boolean = false) {

  def updateRepo(updatedBowlersRepo: BowlersRepository): BowlersRepository = {
    if(hasFinishedAnOver) {
      updatedBowlersRepo.changeBowler
    } else updatedBowlersRepo
  }

  def nextBall(ball: Ball): Inning = {
    val updatedScoreCard = scoreCard.updateScoreCard(ball)
    val repoWithUpdatedBowler = updateRepo(bowlersRepo)
    val repoWithBowledBall = repoWithUpdatedBowler.bowls(ball)

    val isEndOfInning = updatedScoreCard.over.number > totalOvers

    if (ball == Wicket) {
      if (team.nonEmpty) {
        val nextPlayer = team.next
        val outPlayer = if (updatedScoreCard.onStrike.state == Out) updatedScoreCard.onStrike else updatedScoreCard.offStrike
        val updatedTeam = team.wicketOf(outPlayer)

        copy(
          scoreCard = updatedScoreCard.updateOutPlayer(nextPlayer),
          team = updatedTeam.pop(),
          endOfInning = isEndOfInning,
          bowlersRepo = repoWithBowledBall
        )
      } else {
        copy(scoreCard = updatedScoreCard, endOfInning = true, bowlersRepo = repoWithBowledBall)
      }
    } else
      copy(scoreCard = updatedScoreCard, endOfInning = isEndOfInning, bowlersRepo = repoWithBowledBall)
  }

  def hasFinishedAnOver: Boolean = scoreCard.over.number > 1 && scoreCard.over.balls.isEmpty

  def printStats(): Unit = {
    println(s"ScoreCard: ")
    val players = scoreCard.onStrike :: scoreCard.offStrike :: team.outPlayers
    val playerStats = players.map(_.statsString()).mkString("\n")
    println("Batting stats:")
    println(playerStats)

    println("Bowling stats:")
    bowlersRepo.printStats()
    println(s"Score: ${scoreCard.totalScore}/${scoreCard.wickets}, Extras: ${scoreCard.extras}")
    println(s"Overs: ${scoreCard.over.number - 1}.${scoreCard.over.validCount}")
  }

}
