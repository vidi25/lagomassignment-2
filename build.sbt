
name := "lagom-another-microservice-usage"

version := "0.1"

scalaVersion := "2.12.5"

organization in ThisBuild := "edu.knoldus"
scalaVersion in ThisBuild := "2.12.4"
lagomServiceLocatorPort in ThisBuild := 2000
lagomServiceGatewayPort in ThisBuild := 9010

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lazy val `user` = (project in file("."))
  .aggregate(`user-service-api`, `user-service-impl`)

lazy val `user-service-api` = (project in file("user-service-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `user-service-impl` = (project in file("user-service-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      macwire,
      scalaTest,
      "com.knoldus" %% "external-service-api" % "0.1.0-SNAPSHOT"
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`user-service-api`)

lazy val userService = lagomExternalScaladslProject("user-external-service", "com.knoldus" %% "external-service-impl" % "0.1.0-SNAPSHOT")