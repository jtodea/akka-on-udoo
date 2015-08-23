package de.akka.talk.serial

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
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

  val portMock: String = "/dev/ttyp4"
  val serialActorRef = TestActorRef(new SerialActor(portMock, settings))

  "Serial actor" should "open the serial connection" in {
    serialActorRef ! OpenSerialConnection()
  }

  it should "get a reply with a Opened message" in {
    serialActorRef.receive(Serial.Opened(port))
  }

  it should "send data via the serial connection and acknowledge" in {
    serialActorRef ! SendData(ByteString("test"))
    serialActorRef.receive(MyPacketAck(4))
  }

  it should "close the connection and reply with Closed message" in {
    serialActorRef ! CloseSerialConnection()
    serialActorRef.receive(Serial.Closed)
  }
}
