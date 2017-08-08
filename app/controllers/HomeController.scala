package controllers

import javax.inject._

import actors.{HostWebSocketActor, HostActorRunner}
import models.ProfileService
import akka.actor.ActorSystem
import akka.stream.Materializer
import play.api.libs.streams.ActorFlow
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(profileService: ProfileService,
                               cc: ControllerComponents)
                              (implicit system: ActorSystem,
                               mat: Materializer,
                               ec: ExecutionContext) extends AbstractController(cc) {

  val runner = new HostActorRunner(profileService)

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index("Welcome to Websocket Example"))
  }

  def ws : WebSocket = WebSocket.accept[String, String] { request =>
   ActorFlow.actorRef(out => HostWebSocketActor.props(out))
  }
}
