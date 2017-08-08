package entities

import akka.actor.ActorRef

@SerialVersionUID(1941L)
case class Unregister(a:ActorRef) extends Serializable
