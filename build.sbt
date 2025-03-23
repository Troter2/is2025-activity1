name := "is2025-activity1"

ThisBuild / scalaVersion := "3.6.3"

ThisBuild / scalacOptions ++= List("-feature", "-deprecation", "-Ykind-projector:underscores", "-source:future")

ThisBuild / libraryDependencies += "org.scalameta" %% "munit" % "1.1.0" % Test
