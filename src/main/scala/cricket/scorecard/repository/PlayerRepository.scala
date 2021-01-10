package cricket.scorecard.repository

import cricket.scorecard.models.Player

case class PlayerRepository(numPlayers: Int, outPlayers: List[Player], remainingPlayers: List[Player]) {
  def pop(): PlayerRepository = copy(remainingPlayers = remainingPlayers.tail)

  def next: Player = remainingPlayers.head

  def wicketOf(p: Player): PlayerRepository = copy(outPlayers = p :: outPlayers)

  def nonEmpty: Boolean = remainingPlayers.nonEmpty
}
