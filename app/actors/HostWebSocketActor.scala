package actors

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSelection, Props}
import akka.event.{Logging, LoggingReceive}
import entities.{Register, Unregister}
import models.Person
import play.api.Logger
import play.api.libs.json.Json

object HostWebSocketActor{
  def props(out: ActorRef)  = Props(new HostWebSocketActor(out))
}

class HostWebSocketActor(out: ActorRef) extends Actor with ActorLogging  {
  //val log = Logging(context.system, this)
  val actorSelection: ActorSelection = context.actorSelection("akka://application/user/hostActor")

  def receive: PartialFunction[Any, Unit] = LoggingReceive {
    case "init" =>
      //Logger.info("LOG CTX----> HWSA " + context +"HostWebSocketActor :: Init connection Host")
      actorSelection ! Register(self)
      out ! Json.stringify(Json.obj("message" -> "HostWebSocketActor"))
    case None =>
      //Logger.info("LOG CTX----> HWSA" + context + "There are no questions")
      out ! Json.stringify(Json.obj("error" -> "no questions"))
    case Person(fn, ln) =>
      out ! Json.stringify(Json.obj("person" -> Json.obj("firstName" -> fn, "lastName" -> ln)))
  }

  override def postStop(): Unit = {
    actorSelection ! Unregister(self)
  }
}
