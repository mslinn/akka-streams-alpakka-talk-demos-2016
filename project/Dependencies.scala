import sbt._

object Dependencies {
  object V {
    val akka     = "2.4.16"
    val akkaHttp = "10.0.3"
    val akkaStreamKafka = "0.13"
    val akkaStreamContrib = "0.6"
    val logback = "1.2.0"
  }

  object Compile {
    val akkaActor: ModuleID = "com.typesafe.akka"            %% "akka-actor"                 % V.akka              withSources()

    val akkaStream: ModuleID           = "com.typesafe.akka" %% "akka-stream"                % V.akka              withSources()

    val akkaHttpCore: ModuleID         = "com.typesafe.akka" %% "akka-http-core"             % V.akkaHttp          withSources()
    val akkaHttp: ModuleID             = "com.typesafe.akka" %% "akka-http"                  % V.akkaHttp          withSources()
    val akkaHttpSprayJson: ModuleID    = "com.typesafe.akka" %% "akka-http-spray-json"       % V.akkaHttp          withSources()
    val akkaHttpJacksonJson: ModuleID  = "com.typesafe.akka" %% "akka-http-jackson"          % V.akkaHttp          withSources()
    val akkaHttpXml: ModuleID          = "com.typesafe.akka" %% "akka-http-xml"              % V.akkaHttp          withSources()
    val akkaHttpTestkit: ModuleID      = "com.typesafe.akka" %% "akka-http-testkit"          % V.akkaHttp          withSources()
    val akkaStreamTestkit: ModuleID    = "com.typesafe.akka" %% "akka-stream-testkit"        % V.akka              withSources()

    val akkaTestKit: ModuleID          = "com.typesafe.akka" %% "akka-testkit"               % V.akka              withSources()
    val akkaMultiNodeTestKit: ModuleID = "com.typesafe.akka" %% "akka-multi-node-testkit"    % V.akka              withSources()

    // --- ALPAKKA start ---
    val akkaStreamKafka: ModuleID       = "com.typesafe.akka" %% "akka-stream-kafka"         % V.akkaStreamKafka   withSources()
    val akkaStreamContrib: ModuleID     = "com.typesafe.akka" %% "akka-stream-contrib"       % V.akkaStreamContrib withSources()
    val akkaStreamContribAmqp: ModuleID = "com.typesafe.akka" %% "akka-stream-contrib-amqp"  % V.akkaStreamContrib withSources()
    val akkaStreamContribMqtt: ModuleID = "com.typesafe.akka" %% "akka-stream-contrib-mqtt"  % V.akkaStreamContrib withSources()
    val akkaStreamContribXmlDeps: ModuleID = "com.fasterxml"   % "aalto-xml"                 % "1.0.0" // replace with XML module when published
    // --- ALPAKKA end ---

    val akkaSlf4j: ModuleID            = "com.typesafe.akka"  %% "akka-slf4j"                % V.akka              withSources()
    val logbackClassic: ModuleID       = "ch.qos.logback"     %  "logback-classic"           % V.logback           withSources()
  }
  object Test {
    val scalaTest: ModuleID = "org.scalatest" %% "scalatest"  % "3.0.1" % "test" withSources()
    val commonsIo: ModuleID = "commons-io"     % "commons-io" % "2.5"   % "test" withSources()
  }

  import Compile._
  private val testing = Seq(Test.scalaTest, Test.commonsIo)
  private val streams = Seq(akkaStream, akkaStreamTestkit)
  private val logging = Seq(akkaSlf4j, logbackClassic)

  val core: Seq[ModuleID] = Seq(akkaActor, akkaTestKit) ++ streams ++ testing ++ logging
  val engine: Seq[ModuleID] = Seq(akkaActor) ++ testing ++ logging
  val service: Seq[ModuleID] = Seq(akkaActor, akkaHttpCore, akkaHttp, akkaHttpSprayJson, akkaHttpXml, akkaHttpTestkit) ++ testing ++ logging
  val alpakka: Seq[ModuleID] = Seq(akkaStreamKafka, akkaStreamContrib, akkaHttpJacksonJson, akkaHttpSprayJson, akkaStreamContribAmqp, akkaStreamContribMqtt, akkaStreamContribXmlDeps)

  val all: Seq[ModuleID] = core ++ engine ++ service ++ alpakka
}
