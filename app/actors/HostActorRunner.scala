package actors

import akka.actor.{ActorSystem, Props}
import entities.Tick
import models.MyService
import scala.concurrent.duration._
import scala.language.postfixOps


class ApiRunner(myService: MyService) {

  val system: ActorSystem = ActorSystem("ActorSystem")
  implicit val executionContext = system.dispatcher
  private val hostActorRef = system.actorOf(Props(new HostActor(myService)), "hostActor")

  system.scheduler.schedule(0.microseconds, 5.seconds) {
   hostActorRef  ! Tick
  }
}
