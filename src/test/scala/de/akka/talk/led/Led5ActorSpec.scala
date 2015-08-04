package de.akka.talk.led

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestActorRef, TestKit, TestProbe}
import akka.util.ByteString
import de.akka.talk.Constants
import de.akka.talk.led.LedMessages.SendLed5
import de.akka.talk.serial.SerialMessages.SendData
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}


class Led5ActorSpec(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with FlatSpecLike with Matchers with BeforeAndAfterAll with Constants {

  def this() = this(ActorSystem("Led5Spec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Led5 Actor" should "call the serial actor to switch led5 on" in {
    val serialActorRef = TestProbe()
    val actorRef = TestActorRef(new Led5Actor(serialActorRef.ref))

    actorRef ! SendLed5()
    serialActorRef.expectMsg(SendData(ByteString(led5)))
  }
}
