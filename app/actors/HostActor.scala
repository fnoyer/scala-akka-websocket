package actors


import javax.inject.Inject

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.event.LoggingReceive
import entities._
import models.MyService
import scala.collection.mutable
import play.api.Logger


class HostActor @Inject() (myService: MyService) extends Actor with ActorLogging {

  //All the web clients
  private val webClients = mutable.Set[ActorRef]()

  //Send info to website
  def infoWebClients(a: Any): Unit = {
    Logger.info(s"Sending data to ${webClients.size} web clients and the object sent is : ${a}")
    webClients.foreach(ref => ref ! a)
  }

  override def receive: Receive = LoggingReceive {
    case Register(ar) =>
      webClients += ar
      Logger.info(s"Host Actor :: Registering a new web client... There is ${webClients.size} connected client(s)")
    case Unregister(ar) =>
      webClients -= ar
      Logger.info(s"Host Actor :: Unregistering a web client... There is ${webClients.size} remaining connected client(s)")
    case Tick =>
      Logger.info("Tick ")
      if(webClients.nonEmpty) {
        infoWebClients(myService.person)
      }
    case x =>
      Logger.error(s"Received ill conceived message: $x")
      unhandled(x)
  }
}
