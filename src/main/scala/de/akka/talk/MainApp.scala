package de.akka.talk

import akka.actor.ActorSystem
import akka.io.IO
import com.github.jodersky.flow.Serial
import de.akka.talk.led.{Led7Actor, Led5Actor, Led6Actor}
import de.akka.talk.serial.SerialActor
import de.akka.talk.led.LedMessages._


object MainApp extends App with Constants {

  implicit val system = ActorSystem("UdooActorSystem")

  val serialIoRef = IO(Serial)
  val serialActor = system.actorOf(SerialActor(port, settings, serialIoRef))

  val led5Actor = system.actorOf(Led5Actor(serialActor))
  val led6Actor = system.actorOf(Led6Actor(serialActor))
  val led7Actor = system.actorOf(Led7Actor(serialActor))

  while (true) {
    led5Actor ! SendLed5()
    led6Actor ! SendLed6()
    led7Actor ! SendLed7()
  }
}
