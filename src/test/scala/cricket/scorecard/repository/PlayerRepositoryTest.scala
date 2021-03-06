package cricket.scorecard.repository

import cricket.scorecard.models.Player
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerRepositoryTest extends AnyWordSpec with Matchers {
  "wicketOf" should {
    "return repository with out player added in out players list" in {
      val repository = PlayerRepository(3, List(Player("C")), List.empty)

      val updatedRepository = repository.wicketOf(Player("A"))

      updatedRepository.outPlayers.length shouldBe 1
      updatedRepository.outPlayers.head.name shouldBe "A"
    }
  }

  "isEmpty" should {
    "return true when there are players remaining" in {
      val repository = PlayerRepository(2, List(Player("A")), List.empty)

      repository.nonEmpty shouldBe true
    }
  }

  "next" should {
    "return next player from next players list" in {
      val repository = PlayerRepository(3, List(Player("A")), List.empty)

      repository.next shouldBe Player("A")
    }
  }

  "pop" should {
    "return repository with removed next player from next player list" in {
      val repository = PlayerRepository(3, List(Player("A")), List.empty)

      repository.pop().remainingPlayers shouldBe List.empty
    }
  }
}