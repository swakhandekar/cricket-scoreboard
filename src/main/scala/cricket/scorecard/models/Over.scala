package cricket.scorecard.models

case class Over(number: Int, balls: List[Ball])

object Over {
  def empty(number: Int): Over = Over(number, List.empty)
}
