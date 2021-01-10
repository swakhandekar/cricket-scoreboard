package cricket.scorecard.models

sealed trait Ball {
  def runs: Int = extra + valid

  def extra: Int

  def valid: Int
}

case class Runs(valid: Int) extends Ball {
  override def extra: Int = 0
}

case class WideBall(valid: Int = 0) extends Ball {
  override def extra: Int = 1
}

case class NoBall(valid: Int = 0) extends Ball {
  override def extra: Int = 1
}

case object Wicket extends Ball {
  override def extra: Int = 0

  override def valid: Int = 0
}
