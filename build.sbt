name := """akka_talk"""

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= {

  val akkaV = "2.3.9"
  val flowV = "2.1.0"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    "junit" % "junit" % "4.12" % "test",
    "com.novocode" % "junit-interface" % "0.11" % "test",

    "com.github.jodersky" %% "flow" % flowV,
    "com.github.jodersky" % "flow-native" % flowV
  )
}

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")


fork in run := true