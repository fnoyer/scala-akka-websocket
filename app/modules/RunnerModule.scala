package modules

import actors.HostActorRunner
import play.api.inject.{SimpleModule, _}


class RunnerModule extends SimpleModule(bind[HostActorRunner].toSelf.eagerly())
