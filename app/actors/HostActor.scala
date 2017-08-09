package actors

import akka.actor.{Actor, ActorLogging, ActorRef, Identify, Props}
import akka.event.{Logging, LoggingReceive}
import entities._
import models.MyService

import scala.collection.mutable


object HostActor {
  def props(profileService: MyService) = Props(new HostActor(profileService))
}

class HostActor(myService: MyService) extends Actor with ActorLogging {

  //val log = Logging(context.system, this)

  //All the web clients
  private val webClients = mutable.Set[ActorRef]()

  //Send info to website
  def infoWebClients(a: Any): Unit = {
    log.info("Sending to {} web clients message: {}", webClients.size, a)
    webClients.foreach(ref => ref ! a)
  }

  override def receive: Receive = LoggingReceive {
    case Register(ar) =>
      log.info("Host Actor :: Registered Web Client // amount of connected client = " +  webClients.size)
      webClients += ar
    case Unregister(ar) =>
      log.info("Host Actor :: Unregistered Web Client // amount of connected client = " +  webClients.size)
      webClients -= ar
    case Tick =>
      if(webClients.size > 0) {
        log.info("Tick with some already registered clients")
        infoWebClients(myService.person)
      }
      else{
        println(s"${self.path} received Tick")
        log.info("Tick with no registered clients")
      }
    case Identify => sender()
    case x =>
      log.error("Received ill conceived message: {}", x)
      unhandled(x)
  }
}