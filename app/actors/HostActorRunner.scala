package actors

import akka.actor.{ActorRef, ActorSystem, Props}
import entities.Tick
import models.MyService

import scala.concurrent.duration._
import scala.language.postfixOps


class ApiRunner(myService: MyService, actoRef: ActorRef) {

  val system: ActorSystem = ActorSystem("ActorSystem")
  implicit val executionContext = system.dispatcher
  //private val hostActorRef = system.actorOf(Props(hostActor), "hostActor")


  system.scheduler.schedule(0.microseconds, 5.seconds) {
    println("EXECUTION CONTENT FOR DEBUG IS " + executionContext)
    actoRef ! Tick
  }
}
