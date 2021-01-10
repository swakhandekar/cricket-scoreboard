package cricket.scorecard

import cricket.scorecard.models._
import cricket.scorecard.repository.PlayerRepository
import org.mockito.MockitoSugar.{mock, times, verify, when}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ScoreCardTest extends AnyWordSpec with Matchers {
  "updateScoreCard" when {
    "is a valid score" should {
      "return score-card with updated overall score" in {
        val scoreCard = makeTestScoreCard()

        val updatedScoreCard = scoreCard.updateScoreCard(Runs(1))

        updatedScoreCard.totalScore shouldBe 1
      }

      "update individual player score" in {
        val ballToPlay = Runs(1)
        val onStrike = mock[Player]
        when(onStrike.plays(ballToPlay)).thenReturn(onStrike)
        val scoreCard = ScoreCard(0, onStrike, Player("B"))

        scoreCard.updateScoreCard(ballToPlay)

        verify(onStrike, times(1)).plays(ballToPlay)
      }
    }

    "is a wide ball" should {
      "return score card with score updated additionally by one" in {
        val scoreCard = makeTestScoreCard(2)

        val updatedScoreCard = scoreCard.updateScoreCard(WideBall(4))

        updatedScoreCard.totalScore shouldBe 7
      }
    }

    "is a no ball" should {
      "return score card with score updated by one" in {
        val scoreCard = makeTestScoreCard(12)

        val updatedScoreCard = scoreCard.updateScoreCard(NoBall(6))

        updatedScoreCard.totalScore shouldBe 19
      }
    }

    "is a wicket" should {
      "return score card with same score" in {
        val scoreCard = makeTestScoreCard(12)

        val updatedScoreCard = scoreCard.updateScoreCard(Wicket)

        updatedScoreCard.totalScore shouldBe 12
      }
    }

    "should return score-card with addition in bowled balls in an over" in {
      val scoreCard = makeTestScoreCard(12)

      val updatedScoreCard = scoreCard.updateScoreCard(Runs(4))

      val over = updatedScoreCard.over
      over.number shouldBe 1
      val ballsInOver = over.balls
      ballsInOver.size shouldBe 1
      ballsInOver.head shouldBe Runs(4)
    }

    "should return score card with new over when entered ball is valid and last ball of over" in {
      val scoreCard = ScoreCard(13, Player("a"), Player("B"), Over(1, List(Runs(1), Runs(2), Runs(3), Runs(4), Runs(3))))

      val updatedScoreCard = scoreCard.updateScoreCard(Runs(4))

      val over = updatedScoreCard.over
      over.number shouldBe 2
      val ballsInOver = over.balls
      ballsInOver.size shouldBe 0
    }

    "change the player on strike" when {
      "score is 1 and no over change" in {
        val scoreCard = makeTestScoreCard()

        val updatedScoreCard = scoreCard.updateScoreCard(Runs(1))

        updatedScoreCard.onStrike.name shouldBe "Sehwagh"
        updatedScoreCard.offStrike.name shouldBe "Sachin"
      }

      "score is 3 and no over change" in {
        val scoreCard = makeTestScoreCard()

        val updatedScoreCard = scoreCard.updateScoreCard(Runs(3))

        updatedScoreCard.onStrike.name shouldBe "Sehwagh"
        updatedScoreCard.offStrike.name shouldBe "Sachin"
      }

      "over changes and score is neither 1 or 3" in {
        val lastBall = Runs(4)
        val scoreCard = ScoreCard(13, Player("A"), Player("B"), Over(1, List(Runs(1), Runs(2), Runs(3), Runs(4), Runs(3))))

        val updatedScoreCard = scoreCard.updateScoreCard(lastBall)

        updatedScoreCard.onStrike.name shouldBe "B"
        updatedScoreCard.offStrike.name shouldBe "A"
      }
    }
  }

  "updatePlayer" should {
    "update the player on strike with provided player" in {
      val scoreCard = makeTestScoreCard()
      val nextPlayer = Player("C")

      val updatedCard = scoreCard.updatePlayer(nextPlayer)

      updatedCard.onStrike shouldBe Player("C")
    }
  }

  "wickets" should {
    "return number of wickets" in {
      val scoreCard = makeTestScoreCard()

      val updatedCard = scoreCard.updateScoreCard(Runs(1)).updateScoreCard(Wicket).updateScoreCard(Runs(2)).updateScoreCard(Wicket)

      updatedCard.wickets shouldBe 2
    }
  }

  private def makeTestScoreCard(withInitialScore: Int = 0) = {
    ScoreCard(withInitialScore, Player("Sachin"), Player("Sehwagh"))
  }
}
