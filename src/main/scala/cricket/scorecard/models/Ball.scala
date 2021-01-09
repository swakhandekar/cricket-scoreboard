package cricket.scorecard.models

sealed trait Ball {
  def runs: Int
}

case class Runs(runs: Int) extends Ball

case object WideBall extends Ball {
  override def runs: Int = 1
}

case object NoBall extends Ball {
  override def runs: Int = 1
}

case object Wicket extends Ball {
  override def runs: Int = 0
}
