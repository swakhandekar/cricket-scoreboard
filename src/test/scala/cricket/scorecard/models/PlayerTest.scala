package cricket.scorecard.models

import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerTest extends AnyWordSpec with Matchers {
  "plays" when {
    "is a wicket" should {
      "update self status as out" in {
        val player = Player("A", state = NotOut)

        val updatedPlayer = player.plays(Wicket)

        updatedPlayer.state shouldBe Out
      }
    }

    "is any ball" should {
      "update the self stats" in {
        val mockStats = mock[Stats]
        when(mockStats.update(any())).thenReturn(mockStats)
        val player = Player("B", state = NotOut, stats = mockStats)

        player.plays(Runs(1)).plays(Runs(4)).plays(Runs(6)).plays(NoBall()).plays(WideBall()).plays(Wicket)

        verify(mockStats, times(6)).update(any())
      }
    }
  }
}
