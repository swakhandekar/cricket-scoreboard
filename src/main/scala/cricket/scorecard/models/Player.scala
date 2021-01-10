package cricket.scorecard.models

case class Player(name: String, stats: Stats = Stats.empty, state: PlayerState = YetToPlay) {
  def plays(runs: Ball): Player = {
    val playerState = if(runs == Wicket) Out else state

    copy(stats = stats.update(runs), state = playerState)
  }
}

sealed trait PlayerState

case object YetToPlay extends PlayerState

case object NotOut extends PlayerState

case object Out extends PlayerState
