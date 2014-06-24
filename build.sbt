import play.PlayScala

name := """jtt"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  cache,
  ws,
  "net.fehmicansaglam" %% "reactivemongo-extensions-bson" % "0.10.0.3"
)
