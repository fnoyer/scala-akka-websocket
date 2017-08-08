package entities

import akka.actor.ActorRef

@SerialVersionUID(505L)
object Tick extends Serializable

@SerialVersionUID(1940L)
case class Register(a:ActorRef) extends Serializable

@SerialVersionUID(1941L)
case class Unregister(a:ActorRef) extends Serializable
