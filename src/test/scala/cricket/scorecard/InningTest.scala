//package cricket.scorecard
//
//import cricket.scorecard.InningTest.teamRepository
//import cricket.scorecard.models.{Out, Over, Player, Runs, Stats, Wicket}
//import cricket.scorecard.repository.PlayerRepository
//import org.mockito.MockitoSugar.{mock, verify, when}
//import org.scalatest.matchers.should.Matchers
//import org.scalatest.wordspec.AnyWordSpec
//
//class InningTest extends AnyWordSpec with Matchers {
//  "nextBall" should {
//    "update score" in {
//      val mockScoreCard = mock[ScoreCard]
//      val ball = Runs(1)
//      when(mockScoreCard.updateScoreCard(ball)).thenReturn(mockScoreCard)
//      when(mockScoreCard.over).thenReturn(Over(1, List.empty))
//      val matchScoreBoard = Inning(mockScoreCard, 1, teamRepository)
//
//      matchScoreBoard.nextBall(ball)
//
//      verify(mockScoreCard).updateScoreCard(ball)
//    }
//
//    "change player on strike when its a wicket and more players are remaining" in {
//      val scoreCard = ScoreCard(12, Player("P"), Player("Q"), Over(1, List(Runs(1), Runs(2))))
//      val ball = Wicket
//      val nextPlayer = teamRepository.next
//      val matchScoreBoard = Inning(scoreCard, 1, teamRepository)
//
//      val updatedScoreCard = matchScoreBoard.nextBall(ball)
//
//      updatedScoreCard.scoreCard.onStrike shouldBe nextPlayer
//    }
//
//    "update out-players list when a player gets out on non last over balls" in {
//      val scoreCard = ScoreCard(12, Player("P"), Player("Q"), Over(1, List(Runs(1), Runs(2))))
//      val ball = Wicket
//
//      val inning = Inning(scoreCard, 1, teamRepository)
//
//      val updatedInning = inning.nextBall(ball)
//      updatedInning.team.outPlayers.size shouldBe 1
//      updatedInning.team.outPlayers.head shouldBe Player("P", Stats(ballsFaced = 1), Out)
//    }
//
//    "update out-players list when a player gets out on last ball of over" in {
//      val mockScoreCard = ScoreCard(12, Player("P"), Player("Q"),
//        Over(1, List(Runs(1), Runs(2), Runs(2), Runs(1), Runs(3))))
//      val ball = Wicket
//
//      val inning = Inning(mockScoreCard, 1, teamRepository)
//
//      val updatedInning = inning.nextBall(ball)
//      updatedInning.team.outPlayers.size shouldBe 1
//      updatedInning.team.outPlayers.head shouldBe Player("P", Stats(ballsFaced = 1), Out)
//    }
//
//    "mark end of inning when last wicket falls" in {
//      val team = PlayerRepository(2, List.empty)
//      val mockCard = mock[ScoreCard]
//      when(mockCard.updateScoreCard(Wicket)).thenReturn(mockCard)
//      when(mockCard.over).thenReturn(Over(1, List.empty))
//      val inning = Inning(scoreCard = mockCard, 1, team)
//
//      val updatedInning = inning.nextBall(Wicket)
//
//      updatedInning.endOfInning shouldBe true
//    }
//
//    "mark end of innings when all overs are played" in {
//      val team = PlayerRepository(2, List.empty)
//      val over = Over(1, List(Runs(1), Runs(2), Runs(3), Runs(4), Runs(1)))
//      val scoreCard = ScoreCard(10, Player("A"), Player("B"), over)
//      val inning = Inning(scoreCard, 1, team)
//
//      val updatedInning = inning.nextBall(Runs(1))
//
//      updatedInning.endOfInning shouldBe true
//    }
//  }
//}
//
//private object InningTest {
//  val teamRepository: PlayerRepository = PlayerRepository(4, List(Player("A"), Player("B"), Player("C"), Player("D")))
//}
