package de.akka.talk.led

import akka.actor.{ActorRef, Props, Actor}
import akka.util.ByteString
import de.akka.talk.Constants
import de.akka.talk.serial.SerialMessages.SendData
import LedMessages.SendLed6


class Led6Actor(serial: ActorRef) extends Actor with Constants {

  override def receive: Receive = {
    case SendLed6() =>
      serial ! SendData(ByteString(led6))
  }
}

object Led6Actor {
  def apply(serial: ActorRef): Props ={
    Props(classOf[Led6Actor], serial)
  }
}