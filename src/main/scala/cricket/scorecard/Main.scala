package cricket.scorecard

import cricket.scorecard.models.{Ball, Player}
import cricket.scorecard.repository.PlayerRepository

import scala.annotation.tailrec

object Main {
  val (team1Players, team2Players, balls, numOvers) = readFromInput()
  private val inning1 = createInning(team1Players)
  private val inning2 = createInning(team2Players)

  private val cricketMatch = Match(inning1, inning2)

  @tailrec
  def playMatch(ongoingMatch: Match, balls: List[Ball]): Unit = {
    if(ongoingMatch.hasEnded) {
      println(ongoingMatch.result())
    } else {
      val nextBall = balls.head
      playMatch(ongoingMatch.nextBall(nextBall), balls.tail)
    }
  }

  def main(args: Array[String]): Unit = {
    playMatch(cricketMatch, balls)
  }

  private def createInning(players: List[Player]): Inning = {
    val team1 = PlayerRepository(players.length, players.tail.tail)
    Inning(ScoreCard(0, players.head, players.tail.head), numOvers, team1)
  }

  private def readFromInput() = {
    (Input.team1Players, Input.team2Players, Input.balls, Input.numOvers)
  }
}
