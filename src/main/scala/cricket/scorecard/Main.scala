package cricket.scorecard

import cricket.scorecard.Input.{numOvers, team1Players, team2Players}
import cricket.scorecard.models.{Ball, Player, Runs}
import cricket.scorecard.repository.PlayerRepository

import scala.annotation.tailrec

object Main {
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
    playMatch(cricketMatch, Input.balls)
  }

  private def createInning(players: List[Player]): Inning = {
    val team1 = PlayerRepository(players.length, players.tail.tail)
    Inning(ScoreCard(0, players.head, players.tail.head), numOvers, team1)
  }
}
