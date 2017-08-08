package models

import javax.inject._
import akka.actor.ActorSystem

case class Person(nom: String, prenom : String)
case class City(nom : String)


class MyService @Inject()(system: ActorSystem) {
  val person = Person("Jorge","Medec")
  val city= City("Paris")
}