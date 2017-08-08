package models

import javax.inject.Singleton

import akka.actor.ActorSystem
import com.google.inject.Inject


case class Personne(nom: String, prenom : String)
case class Ville(nom : String)



@Singleton
class ProfileService @Inject()(system: ActorSystem) {
  val personne = new Personne("Frederic","Noyer")
  val ville = new Ville("Gland")
}