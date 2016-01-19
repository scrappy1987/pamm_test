import play.sbt.PlayImport.PlayKeys
import play.sbt.routes.RoutesKeys._
import sbt.Keys._
import sbt._

object Settings {
  lazy val basicSettings = Seq(
    version := Version.APP,
    homepage := Some(new URL(Var.APP_URI)),
    organization := Var.APP_ORG,
    organizationHomepage := Some(new URL(Var.APP_ORG_HOME)),
    description := Var.APP_DESC,
    startYear := Some(Var.APP_YEAR),
    licenses := Seq(Var.APP_LIC_NAME -> new URL(Var.APP_LIC_URI)),
    scalaVersion := Version.SCALA,
    scalaBinaryVersion := "2.11",
    autoScalaLibrary := false,
    resolvers ++= Lib.repos,
    scalacOptions := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.8",
      "-language:_",
      "-Xlog-reflective-calls",
      "-Ylog-classpath"
    )
  )

  lazy val serviceSettings = Seq(
    // Play provides two styles of routers, one expects its actions to be injected, the
    // other, legacy style, accesses its actions statically.
    routesGenerator := InjectedRoutesGenerator,
    javaSource in Test := baseDirectory.value  / "test" / "java" / "unit",
    resourceDirectory in Test := baseDirectory.value / "test" / "java" / "resources"
    //PlayKeys.externalizeResources := false
  )
}
