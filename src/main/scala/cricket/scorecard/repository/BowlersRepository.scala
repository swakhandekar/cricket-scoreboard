package cricket.scorecard.repository

import cricket.scorecard.models.{Ball, Bowler}

case class BowlersRepository(nextBowler: Bowler, remainingBowlers: List[Bowler], done: List[Bowler] = List.empty) {
  def printStats() = {
    val allBowlers: List[Bowler] = nextBowler :: remainingBowlers ++ done

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
