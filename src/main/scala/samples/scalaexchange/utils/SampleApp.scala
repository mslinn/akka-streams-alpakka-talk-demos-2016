package samples.scalaexchange.utils

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait SampleApp extends App {
  // --- system / config ---

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val dispatcher = system.dispatcher

  val config = system.settings.config

  val samplesConfig = config.getConfig("samples")
  val port = samplesConfig.getInt("server.port")

  implicit class Rainbow(val s: String) {
    def green: String = Console.GREEN + s + Console.RESET
    def red: String = Console.RED + s + Console.RESET
    def yellow: String = Console.YELLOW + s + Console.RESET
    def bold: String = Console.BOLD + s + Console.RESET
  }

  println(s"\n\nRunning: ${getClass.getSimpleName.replaceAll("\\$", "")}".bold)
}
