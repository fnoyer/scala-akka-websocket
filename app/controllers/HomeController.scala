package controllers

import javax.inject._

import actors.{ApiRunner, HostActor, HostWebSocketActor}
import models.MyService
import akka.actor.{ActorSystem, Props}
import akka.stream.Materializer
import play.api.libs.streams.ActorFlow
import play.api.mvc._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(myService: MyService,
                               cc: ControllerComponents)
                              (implicit sys: ActorSystem,
                               mat: Materializer) extends AbstractController(cc) {

  val system: ActorSystem = ActorSystem("ActorSystem")
  val runner = new ApiRunner(myService)
  //val hostActorRef = system.actorOf(Props(classOf[HostActor], myService), "hostActor")
  //val runner = new ApiRunner(myService,hostActorRef)

  // Props(classOf[Ponger] = ActoRef
  //val ponger = system.actorOf(Props(classOf[Ponger], pinger), "ponger")

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


//private val webSocketActorRef =