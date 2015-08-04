package de.akka.talk.serial

import akka.actor.{ActorSystem, Terminated}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import akka.util.ByteString
import com.github.jodersky.flow.Serial
import de.akka.talk.Constants
import de.akka.talk.serial.SerialActor.MyPacketAck
import de.akka.talk.serial.SerialMessages._
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}


class SerialActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with FlatSpecLike with Matchers with BeforeAndAfterAll with Constants {

  def this() = this(ActorSystem("SerialSpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  val serialIoRef = TestProbe()
  val serialActorRef = TestActorRef(new SerialActor(port, settings, serialIoRef.ref))

  "Serial actor" should "open the serial connection" in {
    serialActorRef ! OpenSerialConnection()
    serialIoRef.expectMsg(Serial.Open(port, settings))
  }

  it should "get a reply with a Opened message" in {
    serialIoRef.reply(Serial.Opened(port))
    serialActorRef.receive(Serial.Opened(port))
  }

  it should "send data via the serial connection and acknowledge" in {
    serialActorRef ! SendData(ByteString("test"))
    serialIoRef.expectMsg(Serial.Write(ByteString("test"), MyPacketAck))
    serialIoRef.reply(MyPacketAck(4))
    serialActorRef.receive(MyPacketAck(4))
  }

  it should "close the connection and reply with Closed message" in {
    serialActorRef ! CloseSerialConnection()
    serialIoRef.expectMsg(Serial.Close)
    serialIoRef.reply(Serial.Closed)
    serialActorRef.receive(Serial.Closed)
    serialActorRef.unwatch(serialIoRef.ref)
  }

  it should "restart the serial connection if it terminates" in {
    serialIoRef.reply(Terminated)
    serialActorRef.receive(OpenSerialConnection())
  }
}
