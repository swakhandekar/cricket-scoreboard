package cricket.scorecard

import cricket.scorecard.models.{Ball, Bowler, Player}
import cricket.scorecard.repository.{BowlersRepository, PlayerRepository}

import scala.annotation.tailrec

object Main {
  val (team1Players, team2Players, balls, numOvers) = readFromInput()
  private val inning1 = createInning(team1Players.map(Player(_)), team2Players.map(Bowler(_)))
  private val inning2 = createInning(team2Players.map(Player(_)), team1Players.map(Bowler(_)))

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

  private def createInning(batsmen: List[Player], bowlers: List[Bowler]): Inning = {
    val team1 = PlayerRepository(batsmen.length, batsmen.tail.tail)
    val bowlersRepository = BowlersRepository.createFrom(bowlers)
    Inning(ScoreCard(0, batsmen.head, batsmen.tail.head), numOvers, team1, bowlersRepository)
  }

  private def readFromInput() = {
    (Input.team1Players, Input.team2Players, Input.balls, Input.numOvers)
  }
}
