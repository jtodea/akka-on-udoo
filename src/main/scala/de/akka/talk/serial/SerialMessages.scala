package de.akka.talk.serial

import akka.util.ByteString

/**
 * Messages to be send over serial connection.
 */
object SerialMessages {
  case class SendData(data: ByteString)
  case class CloseSerialConnection()
  case class OpenSerialConnection()
}
