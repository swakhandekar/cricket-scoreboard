package cricket.scorecard.models

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OverTest extends AnyWordSpec with Matchers {
  "validCount" should {
    "return count of balls except wide and no balls" in {
      val over = Over(1, List(Runs(1), Runs(2), WideBall(), NoBall()))

      over.validCount shouldBe 2
    }
  }

  "isFinished" should {
    "return true when valid balls are 6" in {
      val over = Over(1, List(Runs(1), Runs(2), Runs(6), NoBall(), Runs(4), Runs(4), Runs(1)))

      over.isFinished shouldBe true
    }

    "return false when valid balls are less than 6" in {
      val over = Over(1, List(Runs(1), Runs(2)))

      over.isFinished shouldBe false
    }
  }
}