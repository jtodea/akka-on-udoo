package de.akka.talk

import com.github.jodersky.flow.{Parity, SerialSettings}


trait Constants {

  val led5 = "led5"
  val led6 = "led6"
  val led7 = "led7"

  val port: String = "/dev/ttymxc3"

  private val baud: Int = 115200
  private val characterSize: Int = 8
  private val twoStopBits: Boolean = false
  private val parity: Parity.Parity = Parity(0)

  val settings: SerialSettings = SerialSettings(baud, characterSize, twoStopBits, parity)

}
