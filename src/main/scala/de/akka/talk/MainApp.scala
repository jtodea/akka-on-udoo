package de.akka.talk

import java.util.logging.{Level, Logger}

import akka.actor.ActorSystem
import akka.io.IO
import com.github.jodersky.flow.Serial
import de.akka.talk.led.{Led7Actor, Led5Actor, Led6Actor}
import de.akka.talk.serial.SerialActor
import de.akka.talk.led.LedMessages._
import de.akka.talk.serial.SerialMessages.OpenSerialConnection


object MainApp extends App with Constants {

  val logger = Logger.getLogger("MainApp")

  logger.log(Level.INFO, "starting actor system")
  implicit val system = ActorSystem("UdooActorSystem")
  logger.log(Level.INFO, "started actor system")

  logger.log(Level.INFO, "init io serial")
  val serialIoRef = IO(Serial)
  logger.log(Level.INFO, "init end of io serial")

  logger.log(Level.INFO, "creating serial actor")
  val serialActor = system.actorOf(SerialActor(port, settings, serialIoRef))
  logger.log(Level.INFO, "end creating serial actor")

  logger.log(Level.INFO, "creating led actors")
  val led5Actor = system.actorOf(Led5Actor(serialActor))
  val led6Actor = system.actorOf(Led6Actor(serialActor))
  val led7Actor = system.actorOf(Led7Actor(serialActor))
  logger.log(Level.INFO, "end creating led actors")

  serialActor ! OpenSerialConnection()

  while (true) {
    led5Actor ! SendLed5()
    led6Actor ! SendLed6()
    led7Actor ! SendLed7()
  }
}
