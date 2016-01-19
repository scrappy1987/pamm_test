import Lib._

lazy val root = (project in file("."))
  .aggregate(testsetup, svc, int)
  .settings(libraryDependencies ++= Seq(
    jdbc
  ))


lazy val svc = (project in file("svc"))
  .enablePlugins(PlayJava)
  .settings(clientTest:= clientTestTask)
  .settings(endToEndTest:= endToEndTestTask)
  .settings(startPAMM:= startPAMMTask)
  .settings(stopPAMM:= stopPAMMTask)
  .settings(Keys.test:= customTestTask.value)
  .settings(Settings.basicSettings: _*)
  .settings(Settings.serviceSettings: _*)
  .settings(libraryDependencies ++= Seq(
    javaJpa,
    hibernate,
    cache,
    javaWs,
    evolutions,
    jdbc,
    "com.typesafe.play" %% "play-mailer" % "3.0.1",
    "io.jsonwebtoken" % "jjwt" % "0.6.0"
  ) ++ Lib.test(
    junit
  ))

lazy val int = (project in file("int"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++= Lib.compile(
    jaxrs, jaxrsClient, guice
  ) ++ Lib.test(
    cucumberGuice, cucumberJUnit, dbunit, mysqlconn, junit, logback
  ))

lazy val testsetup = (project in file("testsetup"))
  .enablePlugins(PlayJava)
  .settings(Settings.basicSettings: _*)
  .settings(Settings.serviceSettings: _*)
  .settings(libraryDependencies ++= Seq(
    javaJpa, hibernate, cache, javaWs, evolutions, h2, selenium
  ) ++ Lib.test(
    junit
  ))

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

fork in run := true
