package cricket.scorecard.models

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class StatsTest extends AnyWordSpec with Matchers {
  "update" when {
    "player has scored singles" should {
      "return stats updated with singles" in {
        val stats = Stats()

        val updatedStats = stats.update(Runs(1))

        updatedStats.singles shouldBe 1
      }

      "return stats updated with singles on a wide ball" in {
        val stats = Stats()

        val updatedStats = stats.update(WideBall(3))

        updatedStats.singles shouldBe 3
      }

      "return stats updated with singles on a no ball" in {
        val stats = Stats()

        val updatedStats = stats.update(NoBall(1))

        updatedStats.singles shouldBe 1
      }

    }

    "player has hit a boundary" should {
      "return stats updated with boundaries" in {
        val stats = Stats()

        val updatedStats = stats.update(Runs(4))

        updatedStats.singles shouldBe 0
        updatedStats.boundaries shouldBe 1
      }

      "return stats updated with boundaries on a wide ball" in {
        val stats = Stats()

        val updatedStats = stats.update(WideBall(4))

        updatedStats.boundaries shouldBe 1
      }

      "return stats updated with boundaries on a no ball" in {
        val stats = Stats()

        val updatedStats = stats.update(NoBall(4))

        updatedStats.boundaries shouldBe 1
      }
    }

    "player has hit a six" should {
      "return stats updated with sixes" in {
        val stats = Stats()

        val updatedStats = stats.update(Runs(6))

        updatedStats.singles shouldBe 0
        updatedStats.boundaries shouldBe 0
        updatedStats.sixes shouldBe 1
      }

      "return stats updated with sixes on a no ball" in {
        val stats = Stats()

        val updatedStats = stats.update(NoBall(6))

        updatedStats.sixes shouldBe 1
      }
    }

    "player faces any ball" should {
      "increase the balls faced count" in {
        val stats = Stats()

        val updatedStats = stats.update(Runs(1)).update(WideBall()).update(NoBall()).update(Wicket)

        updatedStats.ballsFaced shouldBe 4
      }
    }

    "player loses wicket" should {
      "return score card with update only in ballsFaced" in {
        val stats = Stats()

        val updatedStats = stats.update(Wicket)

        updatedStats.ballsFaced shouldBe 1
        updatedStats.sixes shouldBe 0
        updatedStats.boundaries shouldBe 0
        updatedStats.singles shouldBe 0
      }
    }
  }
}
