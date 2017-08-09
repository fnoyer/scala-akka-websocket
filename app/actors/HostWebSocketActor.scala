package actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSelection, Props}
import akka.event.{LoggingReceive}
import entities.{Register, Unregister}
import models.Person
import play.api.libs.json.Json

object HostWebSocketActor{
  def props(out: ActorRef)  = Props(new HostWebSocketActor(out))
}

class HostWebSocketActor(out: ActorRef) extends Actor with ActorLogging  {

  val actorSelection: ActorSelection = context.actorSelection("akka://application/user/hostActor")

  def receive: PartialFunction[Any, Unit] = LoggingReceive {
    case "init" =>
      println(s"${self.path} received init")
      actorSelection ! Register(self)
      out ! Json.stringify(Json.obj("message" -> "init message received from web client"))
    case Person(fn, ln) =>
      out ! Json.stringify(Json.obj("person" -> Json.obj("firstName" -> fn, "lastName" -> ln)))
    case None =>
      out ! Json.stringify(Json.obj("error" -> "no questions"))
  }

  override def postStop(): Unit = {
    actorSelection ! Unregister(self)
  }
}
