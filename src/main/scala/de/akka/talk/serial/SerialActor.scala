package de.akka.talk.serial

import java.nio.charset.StandardCharsets

import akka.actor._
import akka.io.IO
import akka.util.ByteString
import com.github.jodersky.flow.Serial._
import com.github.jodersky.flow.{Serial, SerialSettings}
import de.akka.talk.serial.SerialActor.MyPacketAck
import de.akka.talk.serial.SerialMessages._


/**
 * Serial communication Actor. Used for Arduino GPIO.
 */
class SerialActor(port: String, serialSettings: SerialSettings) extends Actor with ActorLogging {

  import context.system

  override def receive: Receive = {
    case OpenSerialConnection() =>
      IO(Serial) ! Serial.Open(port, serialSettings)

    case CommandFailed(cmd: Open, reason) =>
      log.info(s"Command: $cmd failed! Reason: ${reason.getMessage} ### Stacktrace: ${reason.getCause}")

    case Opened(port: String) =>
      log.info(s"Serial connection opened on port: $port.")
      val serial = sender()
      context become openedSerial(serial)
      context watch serial
  }

  def openedSerial(serial: ActorRef): Receive = {
    case Closed =>
      log.info("Serial connection closed")
      context unwatch serial
      context unbecome()

    case Terminated(`serial`) =>
      log.info("Serial connection crashed!")
      context unwatch serial
      context unbecome()
      self ! OpenSerialConnection()

    case SendData(data: ByteString) =>
      log.info(s"Sending data to serial: ${data.decodeString(StandardCharsets.UTF_8.name())}.")
      serial ! Write(data, n => MyPacketAck(n))

    case MyPacketAck(x: Int) =>
      log.info(s"Wrote $x bytes to serial")

    case CloseSerialConnection() =>
      log.info("Trying to close the serial connection")
      serial ! Serial.Close

    case Received(data: ByteString) =>
      log.info("Received: " + data.decodeString(StandardCharsets.UTF_8.name()))
  }

}

object SerialActor {

  def apply(port: String, settings: SerialSettings): Props = {
    Props(classOf[SerialActor], port, settings)
  }

  case class MyPacketAck(wrote: Int) extends Event
}
