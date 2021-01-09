package cricket.scorecard.models

sealed trait Ball {
  def runs: Int
}

case class Runs(runs: Int) extends Ball

case class WideBall(r: Int = 0) extends Ball {
  override def runs: Int = r + 1
}

case class NoBall(r: Int = 0) extends Ball {
  override def runs: Int = r + 1
}

case object Wicket extends Ball {
  override def runs: Int = 0
}
