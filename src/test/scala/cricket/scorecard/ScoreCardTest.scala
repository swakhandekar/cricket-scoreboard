package cricket.scorecard

import cricket.scorecard.models.{NoBall, Over, Runs, Wicket, WideBall}
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
      "return score card with score updated additionally by one" in {
        val scoreCard = ScoreCard(2)

        val updatedScoreCard = scoreCard.nextBall(WideBall(4))

        updatedScoreCard.totalScore shouldBe 7
      }
    }

    "is a no ball" should {
      "return score card with score updated by one" in {
        val scoreCard = ScoreCard(12)

        val updatedScoreCard = scoreCard.nextBall(NoBall(6))

        updatedScoreCard.totalScore shouldBe 19
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

    "should return score card with new over when entered ball is valid and last ball of over" in {
      val scoreCard = ScoreCard(13, Over(1, List(Runs(1), Runs(2), Runs(3), Runs(4), Runs(3))))

      val updatedScoreCard = scoreCard.nextBall(Runs(4))

      val over = updatedScoreCard.over
      over.number shouldBe 2
      val ballsInOver = over.balls
      ballsInOver.size shouldBe 0
    }
  }
}
