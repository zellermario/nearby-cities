ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.5.1"

lazy val root = (project in file("."))
  .settings(
    name := "nearby-cities"
  )

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "2.0.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"
libraryDependencies += "org.typelevel" %% "squants" % "1.8.3"
libraryDependencies += "com.google.geometry" % "s2-geometry" % "2.0.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.4"
