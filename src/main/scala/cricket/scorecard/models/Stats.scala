package cricket.scorecard.models

case class Stats(singles: Int = 0, boundaries: Int = 0, sixes: Int = 0, ballsFaced: Int = 0) {

  def update(ball: Ball): Stats = {
    val updatedBalls = ballsFaced + 1
    ball.valid match {
      case 6 => copy(sixes = sixes + 1, ballsFaced = updatedBalls)
      case 4 => copy(boundaries = boundaries + 1, ballsFaced = updatedBalls)
      case s => copy(singles = singles + s, ballsFaced = updatedBalls)
    }
  }

  def totalScore: Int = singles + 4 * boundaries + 6 * sixes

  override def toString: String = {
    s"Total: $totalScore, Singles: $singles, Boundaries: $boundaries, Sixes: $sixes, BallsFaced: $ballsFaced"
  }
}

object Stats {
  def empty: Stats = Stats()
}
