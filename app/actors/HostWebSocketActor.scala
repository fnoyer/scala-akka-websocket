package actors

import akka.actor.{Actor, ActorRef, ActorSelection, Props}
import entities.{Register, Unregister}
import play.api.Logger
import play.api.libs.json.Json

object HostWebSocketActor{
  def props(out: ActorRef) = Props(new HostWebSocketActor(out))
}

class HostWebSocketActor(out: ActorRef) extends Actor {

  val actorSelection: ActorSelection =
    context.actorSelection("akka://ActorSystem/user/hostActor")

  def receive: PartialFunction[Any, Unit] = {
    case "init" =>
      Logger.debug("HostWebSocketActor :: Init connection Host")
      actorSelection ! Register(self)
      out ! Json.stringify(Json.obj("message" -> "HostWebSocketActor"))
    case None =>
      Logger.debug("There are no questions")
      out ! Json.stringify(Json.obj("error" -> "no questions"))
  }

  override def postStop(): Unit = {
    actorSelection ! Unregister(self)
  }
}