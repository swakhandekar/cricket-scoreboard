package cricket.scorecard.repository

import cricket.scorecard.models.{Ball, Bowler}

case class BowlersRepository(nextBowler: Bowler, remainingBowlers: List[Bowler], done: List[Bowler] = List.empty) {
  def printStats(): Unit = {
    val allBowlers: List[Bowler] = done.reverse ++ (nextBowler :: remainingBowlers)

    allBowlers.foreach(b => b.printStats())
  }

  def bowls(ball: Ball): BowlersRepository = {
    copy(nextBowler = nextBowler.balls(ball))
  }


  def changeBowler: BowlersRepository = {
    if(remainingBowlers.isEmpty) {
      val newRemaining = (nextBowler :: done).reverse
      copy(
        nextBowler = newRemaining.head,
        remainingBowlers = newRemaining.tail,
        done = List.empty
      )
    } else {
      copy(
        nextBowler = remainingBowlers.head,
        remainingBowlers = remainingBowlers.tail,
        done = nextBowler :: done
      )
    }
  }
}

object BowlersRepository {
  def createFrom(list: List[Bowler]): BowlersRepository = {
    BowlersRepository(list.head, list.tail, List.empty)
  }
}
