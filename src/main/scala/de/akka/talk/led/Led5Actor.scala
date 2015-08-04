package de.akka.talk.led

import akka.actor.{ActorRef, Props, Actor}
import LedMessages.SendLed5
import akka.util.ByteString
import de.akka.talk.Constants
import de.akka.talk.serial.SerialMessages.SendData


class Led5Actor(serial: ActorRef) extends Actor with Constants {

  override def receive: Receive = {
    case SendLed5() =>
      serial ! SendData(ByteString(led5))
  }
}

object Led5Actor {
  def apply(serial: ActorRef): Props ={
    Props(classOf[Led5Actor], serial)
  }
}