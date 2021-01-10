package cricket.scorecard

import cricket.scorecard.models.Runs
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MatchTest extends AnyWordSpec with Matchers {
  "nextBall" when {
    "is first inning" should {
      "return match with updated first inning" in {
        val nextBall = Runs(1)
        val mockInning1 = mock[Inning]
        val updatedInning = mock[Inning]
        when(mockInning1.nextBall(nextBall)).thenReturn(updatedInning)
        val mockInning2 = mock[Inning]
        val cricketMatch = Match(mockInning1, mockInning2)

        val updatedMatch = cricketMatch.nextBall(nextBall)

        updatedMatch.inning1 shouldBe updatedInning
      }

      "mark isSecondInning to true when first inning is finished" in {
        val nextBall = Runs(1)
        val mockInning1 = mock[Inning]
        val updatedInning = mock[Inning]
        when(updatedInning.endOfInning).thenReturn(true)
        when(mockInning1.nextBall(nextBall)).thenReturn(updatedInning)
        val mockInning2 = mock[Inning]
        val cricketMatch = Match(mockInning1, mockInning2)

        val updatedMatch = cricketMatch.nextBall(nextBall)

        updatedMatch.inning1 shouldBe updatedInning
        updatedMatch.isSecondInning shouldBe true
      }
    }

    "is second inning" should {
      "return match with updated second inning" in {
        val nextBall = Runs(1)
        val mockInning1 = mock[Inning]
        val mockInning2 = mock[Inning]
        val updatedInning = mock[Inning]
        when(mockInning2.nextBall(nextBall)).thenReturn(updatedInning)
        val cricketMatch = Match(mockInning1, mockInning2, isSecondInning = true)

        val updatedMatch = cricketMatch.nextBall(nextBall)

        updatedMatch.inning2 shouldBe updatedInning
      }

      "mark match finished when second inning is over" in {
        val nextBall = Runs(1)
        val mockInning1 = mock[Inning]
        val mockInning2 = mock[Inning]
        val updatedInning = mock[Inning]
        when(updatedInning.endOfInning).thenReturn(true)
        when(mockInning2.nextBall(nextBall)).thenReturn(updatedInning)
        val cricketMatch = Match(mockInning1, mockInning2, isSecondInning = true)

        val updatedMatch = cricketMatch.nextBall(nextBall)

        updatedMatch.hasEnded shouldBe true
      }
    }
  }
}
