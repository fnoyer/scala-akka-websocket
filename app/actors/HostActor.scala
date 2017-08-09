package actors


import javax.inject.Inject
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.event.{Logging, LoggingReceive}
import entities._
import models.MyService

import scala.collection.mutable
import play.api.Logger

class HostActor @Inject() (myService: MyService) extends Actor with ActorLogging {

  //All the web clients
  private val webClients = mutable.Set[ActorRef]()

  //Send info to website
  def infoWebClients(a: Any): Unit = {
    Logger.info(s"Sending to ${webClients.size} web clients message: $a")
    webClients.foreach(ref => ref ! a)
  }

  override def receive: Receive = LoggingReceive {
    case Register(ar) =>
      Logger.info(s"Host Actor :: Registered Web Client // amount of connected clients ${webClients.size}")
      webClients += ar
    case Unregister(ar) =>
      Logger.info(s"Host Actor :: Unregistered Web Client // amount of connected client ${webClients.size}")
      webClients -= ar
    case Tick =>
      if(webClients.nonEmpty) {
        Logger.info("Tick with some already registered clients")
        infoWebClients(myService.person)
      }
      else{
        Logger.info("Tick with no registered clients")
      }
    case Identify => sender()
    case x =>
      Logger.error(s"Received ill conceived message: $x")
      unhandled(x)
  }
}
