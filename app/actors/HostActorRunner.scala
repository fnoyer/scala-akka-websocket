package actors

import akka.actor.{ActorSystem, Props}
import entities.Tick
import models.ProfileService

import scala.concurrent.duration._
import scala.language.postfixOps


class HostActorRunner(profileService: ProfileService) {

  val system: ActorSystem = ActorSystem("ActorSystem")
  implicit val executionContext = system.dispatcher
  //val hostActorRef = system.actorOf(Props(classOf[HostActor], profileService))
  val hostActorRef = system.actorOf(Props(new HostActor(profileService)), "hostActor")

  system.scheduler.schedule(0.microseconds, 5.seconds) {
    hostActorRef ! Tick
  }
}
