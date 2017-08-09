package actors

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import entities.Tick
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

class HostActorRunner @Inject()(system:ActorSystem, @Named("hostActor") hostActorRef: ActorRef)(implicit executionContext: ExecutionContext) {

  /*
   * The scheduler set to send a message every 5 seconde
   */
  system.scheduler.schedule(0.microseconds, 5.seconds) {
    hostActorRef ! Tick
  }
}
