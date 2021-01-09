package cricket.scorecard.models

case class Over(number: Int, balls: List[Ball]) {
  def validCount: Int = balls.count(b => b != WideBall && b != NoBall)

  def isFinished: Boolean = validCount == 6
}

object Over {
  def empty(number: Int): Over = Over(number, List.empty)
}
