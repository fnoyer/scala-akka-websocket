package actors

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import entities._
import models.ProfileService
import scala.collection.mutable


object HostActor {
  def props(profileService: ProfileService) = Props(new HostActor(profileService))
}

class HostActor(profileService: ProfileService) extends Actor {

  val log = Logging(context.system, this)

  //All the web clients
  private val webClients = mutable.Set[ActorRef]()

  //Send info to website
  def infoWebClients(a: Any): Unit = {
    log.info("Sending to {} web clients message: {}", webClients.size, a)
    webClients.foreach(ref => ref ! a)
  }

  override def receive: Receive = {
    case Register(ar) =>
      log.info("Host Actor :: Registered Web Client // amount of connected client = " +  webClients.size)
      webClients += ar
    case Unregister(ar) =>
      log.info("Host Actor :: Unregistered Web Client // amount of connected client = " +  webClients.size)
      webClients -= ar
    case Tick =>
      if(webClients.size > 0) {
        log.info("Tick with some already registered clients")
        infoWebClients(profileService.personne)
      }
      else{
        log.info("Tick with no registered clients")
      }
    case x =>
      log.error("Received ill conceived message: {}", x)
      unhandled(x)
  }
}