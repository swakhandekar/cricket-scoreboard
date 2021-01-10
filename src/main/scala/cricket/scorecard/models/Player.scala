package cricket.scorecard.models

case class Player(name: String, stats: Stats = Stats.empty, state: PlayerState = NotOut) {
  def statsString(): String = {
    s"Name: $name, ${stats.toString}, $state"
  }

  def plays(runs: Ball): Player = {
    val playerState = if(runs == Wicket) Out else state

    copy(stats = stats.update(runs), state = playerState)
  }
}

sealed trait PlayerState

case object NotOut extends PlayerState

case object Out extends PlayerState
