package cricket.scorecard.repository

import cricket.scorecard.models.{Bowler, Player, Runs}
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BowlersRepositoryTest extends AnyWordSpec with Matchers {

  "bowls" should {
    "replace the bowler in list with updated stats" in {
      val currentBowler = mock[Bowler]
      val ball = Runs(2)
      when(currentBowler.balls(ball)).thenReturn(currentBowler)
      val bowlers = List(Bowler("B"), Bowler("C"))
      val bowlersRepository = BowlersRepository(currentBowler, bowlers)

      val repo = bowlersRepository.bowls(ball)

      verify(currentBowler.balls(ball), times(1))
      repo.nextBowler shouldBe currentBowler
    }
  }

  "changeBowler" should {
    "update the current bowler with next bowler in line" in {
      val currentBowler = Bowler("A")
      val bowlers = List(Bowler("B"), Bowler("C"))
      val bowlersRepository = BowlersRepository(currentBowler, bowlers)

      val updatedRepository = bowlersRepository.changeBowler

      updatedRepository.nextBowler shouldBe Bowler("B")
    }

    "update the current bowler with first bowler when no baller in next list remaining" in {
      val currentBowler = Bowler("C")
      val doneBowlers = List(Bowler("B"), Bowler("A"))
      val bowlersRepository = BowlersRepository(currentBowler, List.empty, doneBowlers)

      val updatedRepository = bowlersRepository.changeBowler

      updatedRepository.nextBowler shouldBe Bowler("A")
    }
  }
}
