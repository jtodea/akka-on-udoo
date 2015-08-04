package de.akka.talk.led

import akka.actor.{ActorRef, Props, Actor}
import LedMessages.SendLed7
import akka.util.ByteString
import de.akka.talk.Constants
import de.akka.talk.serial.SerialMessages.SendData


class Led7Actor(serial: ActorRef) extends Actor with Constants {

  override def receive: Receive = {
    case SendLed7() =>
      serial ! SendData(ByteString(led7))
  }
}

object Led7Actor {
  def apply(serial: ActorRef): Props ={
    Props(classOf[Led7Actor], serial)
  }
}