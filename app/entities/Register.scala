package entities

import akka.actor.ActorRef

@SerialVersionUID(1940L)
case class Register(a:ActorRef) extends Serializable
