package semper.sil.components

/** Describes a component that has a lifetime. That is, it must be started
  * before it can be used (in whatever sense) and that can be stopped, after
  * which it cannot be used anymore. The trait essentially describes a state
  * machine with the transitions initialised -> started -> stopped.
  *
  * The implementing component is free to be more permissive regarding the
  * expected sequence of events (start, stop).
  */
trait LifetimeComponent {
  def start()
  def stop()
}

/** Describes a component that potentially has state which must be taken care
  * of before such a component can be reused (in whatever sense).
  * The trait is intended to describe a state machine that can be started
  * (once), after which it can be reset and reused (arbitrarily often), after
  * which it can be stopped (once).
  *
  * The implementing component is free to be more permissive regarding the
  * expected sequence of events (start, reset, stop).
  */
trait StatefulComponent extends LifetimeComponent {
  def reset()
}