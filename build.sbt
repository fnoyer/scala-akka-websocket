import sbt.Keys.version

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.12.2",
  scalacOptions ++= Seq("-feature", "-deprecation"),
  javacOptions ++= Seq("-Xlint:unchecked")
)


lazy val root = project.in(file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayFilters)
  .settings(
    commonSettings,
    name := "scala-akka",
    libraryDependencies ++= Seq(
      jdbc,
      guice,
      ws,
      "com.typesafe.akka" %% "akka-remote" % "2.4.14",
      "com.typesafe.akka" %% "akka-actor" % "2.5.3",
      "org.scalatestplus.play" % "scalatestplus-play_2.12" % "3.1.1" % "test",
      "org.webjars" % "webjars-play_2.11" % "2.5.0-4",
      "org.webjars" % "bootstrap" % "3.3.7-1",
      "org.webjars" % "rxjs" % "2.5.3",
      "org.webjars" % "RxJS-DOM" % "4.0.1"
    )
  )
  .disablePlugins(PlayFilters)
  
herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/my-app -Dhttp.port=$PORT",
  "worker" -> "java -jar target/universal/stage/lib/my-worker.jar"
)