scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-encoding", "UTF-8"
)

lazy val commonSettings = Seq(
  organization := "com.lightbend.akka",
  name := "javaone",
  version := "1.0",
  scalaVersion := "2.12.1"
)

commonSettings

libraryDependencies ++= Dependencies.all

fork in run := true
