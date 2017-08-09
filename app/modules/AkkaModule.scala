package modules

import actors.HostActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class AkkaModule extends AbstractModule with AkkaGuiceSupport {
  def configure(): Unit = {
    bindActor[HostActor]("hostActor")
  }
}
