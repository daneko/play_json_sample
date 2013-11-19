name := "hello"

version := "1.0"

scalaVersion := "2.10.3"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++=Seq(
  "com.typesafe.play" %% "play-json" % "2.2.1",
  "org.specs2"        %% "specs2"    % "2.3.4" % "test"
)
