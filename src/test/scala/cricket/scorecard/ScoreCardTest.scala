package cricket.scorecard

import cricket.scorecard.models.{NoBall, Runs, Wicket, WideBall}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScoreCardTest extends AnyWordSpec with Matchers {
  "nextBall" when {
    "is a valid score" should {
      "return score-card with updated overall score" in {
        val scoreCard = ScoreCard(0)

        val updatedScoreCard = scoreCard.nextBall(Runs(1))

        updatedScoreCard.totalScore shouldBe 1
      }
    }

    "is a wide ball" should {
      "return score card with score updated by one" in {
        val scoreCard = ScoreCard(2)

        val updatedScoreCard = scoreCard.nextBall(WideBall)

        updatedScoreCard.totalScore shouldBe 3
      }
    }

    "is a no ball" should {
      "return score card with score updated by one" in {
        val scoreCard = ScoreCard(12)

        val updatedScoreCard = scoreCard.nextBall(NoBall)

        updatedScoreCard.totalScore shouldBe 13
      }
    }

    "is a wicket" should {
      "return score card with same score" in {
        val scoreCard = ScoreCard(12)

        val updatedScoreCard = scoreCard.nextBall(Wicket)

        updatedScoreCard.totalScore shouldBe 12
      }
    }

    "should return score-card with addition in bowled balls in an over" in {
      val scoreCard = ScoreCard(12)

      val updatedScoreCard = scoreCard.nextBall(Runs(4))

      val over = updatedScoreCard.over
      over.number shouldBe 1
      val ballsInOver = over.balls
      ballsInOver.size shouldBe 1
      ballsInOver.head shouldBe Runs(4)
    }
  }
}
