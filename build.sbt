name := """akka_talk"""

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "Flow" at "http://repo.spring.io/libs-release-remote"

libraryDependencies ++= {

  val akkaV = "2.4.0"
  val flowV = "2.3.0"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "junit" % "junit" % "4.12" % "test",
    "com.novocode" % "junit-interface" % "0.11" % "test",

    "com.github.jodersky" % "flow_2.11" % flowV
    //"com.github.jodersky" % "flow-native" % flowV % "runtime"
  )
}

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

fork in run := true