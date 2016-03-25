package de.akka.talk

import java.util.logging.{Level, Logger}

import akka.actor.ActorSystem
import de.akka.talk.led.LedMessages._
import de.akka.talk.led.{Led5Actor, Led6Actor, Led7Actor}
import de.akka.talk.serial.SerialActor
import de.akka.talk.serial.SerialMessages.{CloseSerialConnection, OpenSerialConnection}


object MainApp extends App with Constants {

  val logger = Logger.getLogger("MainApp")

  logger.log(Level.INFO, "starting actor system")
  implicit val system = ActorSystem("UdooActorSystem")
  logger.log(Level.INFO, "started actor system")

  logger.log(Level.INFO, "creating serial actor")
  val serialActor = system.actorOf(SerialActor(port, settings))
  logger.log(Level.INFO, "end creating serial actor")

  logger.log(Level.INFO, "creating led actors")
  val led5Actor = system.actorOf(Led5Actor(serialActor))
  val led6Actor = system.actorOf(Led6Actor(serialActor))
  val led7Actor = system.actorOf(Led7Actor(serialActor))
  logger.log(Level.INFO, "end creating led actors")

  serialActor ! OpenSerialConnection()

  logger.log(Level.INFO, "starting serial")
  Thread.sleep(3000)

  led5Actor ! SendLed5()
  logger.log(Level.INFO, "send 5")
  Thread.sleep(3000)

  led6Actor ! SendLed6()
  logger.log(Level.INFO, "send 6")
  Thread.sleep(3000)

  led7Actor ! SendLed7()
  logger.log(Level.INFO, "send 7")
  Thread.sleep(3000)

  serialActor ! CloseSerialConnection()
  logger.log(Level.INFO, "closing serial")

  system.shutdown()
}
