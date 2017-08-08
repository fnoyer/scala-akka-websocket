package models

import javax.inject._
import akka.actor.ActorSystem

case class Personne(nom: String, prenom : String)
case class Ville(nom : String)


class ProfileService @Inject()(system: ActorSystem) {
  val personne = Personne("Frederic","Noyer")
  val ville = Ville("Gland")
}