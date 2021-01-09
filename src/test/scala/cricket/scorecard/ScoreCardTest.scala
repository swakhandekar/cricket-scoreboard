package cricket.scorecard

import cricket.scorecard.models.{NoBall, Runs, Wicket, WideBall}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScoreCardTest extends AnyWordSpec with Matchers {
  "nextBall" when {
    "stat is a valid score" should {
      "return score-card with updated overall score" in {
        val scoreCard = ScoreCard(0)

        val updatedScoreCard = scoreCard.nextBall(Runs(1))

        updatedScoreCard.totalScore shouldBe 1
      }
    }

    "stat is a wide ball" should {
      "return score card with score updated by one" in {
        val scoreCard = ScoreCard(2)

        val updatedScoreCard = scoreCard.nextBall(WideBall)

        updatedScoreCard.totalScore shouldBe 3
      }
    }

    "stat is a no ball" should {
      "return score card with score updated by one" in {
        val scoreCard = ScoreCard(12)

        val updatedScoreCard = scoreCard.nextBall(NoBall)

        updatedScoreCard.totalScore shouldBe 13
      }
    }

    "stat is a wicket" should {
      "return score card with same score" in {
        val scoreCard = ScoreCard(12)

        val updatedScoreCard = scoreCard.nextBall(Wicket)

        updatedScoreCard.totalScore shouldBe 12
      }
    }
  }
}
