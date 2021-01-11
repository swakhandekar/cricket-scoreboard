package cricket.scorecard.models

case class BowlingStats(numberOfBalls: Int = 0, wickets: Int = 0, extras: Int = 0) {
  def update(ball: Ball): BowlingStats = {

    copy(
      numberOfBalls = numberOfBalls + 1,
      wickets = if(ball == Wicket) wickets + 1 else wickets,
      extras = if(ball.isInstanceOf[WideBall] || ball.isInstanceOf[NoBall]) extras + 1 else extras
    )
  }




}
