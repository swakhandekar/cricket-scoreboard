package cricket.scorecard.models

import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class BowlerTest extends AnyWordSpec with Matchers {
  "balls" should {
    "return bowler updated with bowler stats" in {
      val mockStats = mock[BowlingStats]
      val returnedStats = mock[BowlingStats]
      val ball = Runs(1)
      when(mockStats.update(ball)).thenReturn(returnedStats)
      val bowler = Bowler("A", stats = mockStats)

      val updatedBowler = bowler.balls(ball)
      updatedBowler.stats shouldBe BowlingStats()

      verify(mockStats.update(ball), times(1))
      updatedBowler.stats shouldBe returnedStats
    }
  }
}
