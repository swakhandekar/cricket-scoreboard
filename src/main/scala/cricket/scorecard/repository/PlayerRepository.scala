package cricket.scorecard.repository

import cricket.scorecard.models.Player

case class PlayerRepository(numPlayers: Int, remainingPlayers: List[Player], outPlayers: List[Player] = List.empty) {
  def pop(): PlayerRepository = copy(remainingPlayers = remainingPlayers.tail)

  def next: Player = remainingPlayers.head

  def wicketOf(p: Player): PlayerRepository = copy(outPlayers = p :: outPlayers)

  def nonEmpty: Boolean = remainingPlayers.nonEmpty
}
