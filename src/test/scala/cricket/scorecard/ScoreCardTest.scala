package cricket.scorecard

import cricket.scorecard.models.Score
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScoreCardTest extends AnyWordSpec with Matchers {
  "nextBall" when {
    "stat is a valid score" should {
      "return score-card with updated overall score" in {
        val scoreCard = ScoreCard(0)

        val updatedScoreCard = scoreCard.nextBall(Score(1))

        updatedScoreCard.totalScore shouldBe 1
      }
    }
  }
}
