import sbt.Keys.version

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.2",
  scalacOptions ++= Seq("-feature", "-deprecation"),
  javacOptions ++= Seq("-Xlint:unchecked")
)


lazy val root = project.in(file("."))
  .enablePlugins(PlayScala)
  .settings(
    commonSettings,
    name := "scala-akka",
    libraryDependencies ++= Seq(
      ws,
      guice,
      jdbc,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test,
      "com.typesafe.play" %% "play-mailer" % "6.0.0",
      "com.typesafe.play" %% "play-mailer-guice" % "6.0.0",
      "com.twilio.sdk" % "twilio-java-sdk" % "6.3.0",
      "com.twilio.sdk" % "twilio" % "7.13.1",
      "mysql" % "mysql-connector-java" % "5.1.41",
      "com.typesafe.play" %% "anorm" % "2.6.0-M1",
      "org.webjars" % "bootstrap" % "3.3.2",
      "org.webjars" % "metisMenu" % "2.5.2",
      "org.webjars" % "morrisjs" % "0.5.1",
      "org.webjars" % "font-awesome" % "4.6.3",
      "org.webjars" % "flot" % "0.8.3",
      "org.webjars" % "datatables" % "1.10.12",
      "org.webjars" % "datatables-plugins" % "1.10.12",
      "org.webjars" %% "webjars-play" % "2.6.1",
      "com.typesafe.akka" %% "akka-actor" % "2.4.19",
      "com.typesafe.akka" %% "akka-testkit" % "2.4.19" % "test",
      "com.typesafe.akka" % "akka-remote_2.12" % "2.4.19",
      "org.webjars" % "rxjs" % "2.5.3",
      "org.webjars" % "RxJS-DOM" % "4.0.1",
      "org.webjars" % "requirejs" % "2.2.0"
    )
  )
  .disablePlugins(PlayFilters)