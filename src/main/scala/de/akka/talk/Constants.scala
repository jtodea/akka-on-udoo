package de.akka.talk

import com.github.jodersky.flow.{Parity, SerialSettings}


trait Constants {

  val led5 = "5"
  val led6 = "6"
  val led7 = "7"

  val port: String = "/dev/ttymxc3"

  private val baud: Int = 9600
  private val characterSize: Int = 8
  private val twoStopBits: Boolean = false
  private val parity: Parity.Parity = Parity(0)

  val settings: SerialSettings = SerialSettings(baud, characterSize, twoStopBits, parity)

}
