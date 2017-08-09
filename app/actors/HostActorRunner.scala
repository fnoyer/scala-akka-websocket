package actors

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem, Props}
import entities.Tick
import play.api.Logger
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps


class HostActorRunner @Inject()(system:ActorSystem, @Named("hostActor") hostActorRef: ActorRef)(implicit executionContext: ExecutionContext) {

  system.scheduler.schedule(0.microseconds, 5.seconds) {
    Logger.info("EXECUTION CONTENT FOR DEBUG IS " + executionContext)
    hostActorRef ! Tick
  }
}
