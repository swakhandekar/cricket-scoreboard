package cricket.scorecard.models

case class Bowler(name: String, stats: BowlingStats = BowlingStats()) {
  def printStats(): Unit= {
    println(s"Name: $name, Number of Balls: ${stats.numberOfBalls}, total extras: ${stats.extras}, Wickets: ${stats.wickets}")
  }

  def balls(ball: Ball): Bowler = {
    copy(stats = stats.update(ball))
  }
}
