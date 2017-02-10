import sbt._

object Dependencies {
  object Version {
    val akka     = "2.4.16"
    val akkaHttp = "10.0.3"
    val akkaStreamKafka = "0.13"
    val akkaStreamContrib = "0.6"
    val logback = "1.2.0"
  }

  object Compile {
    val akkaActor            = "com.typesafe.akka" %% "akka-actor"                        % Version.akka

    val akkaStream           = "com.typesafe.akka" %%"akka-stream"                        % Version.akka

    val akkaHttpCore         = "com.typesafe.akka" %% "akka-http-core"                    % Version.akkaHttp
    val akkaHttp             = "com.typesafe.akka" %% "akka-http"                         % Version.akkaHttp
    val akkaHttpSprayJson    = "com.typesafe.akka" %% "akka-http-spray-json"              % Version.akkaHttp
    val akkaHttpJacksonJson  = "com.typesafe.akka" %% "akka-http-jackson"                 % Version.akkaHttp
    val akkaHttpXml          = "com.typesafe.akka" %% "akka-http-xml"                     % Version.akkaHttp
    val akkaHttpTestkit      = "com.typesafe.akka" %% "akka-http-testkit"                 % Version.akkaHttp
    val akkaStreamTestkit    = "com.typesafe.akka" %% "akka-stream-testkit"               % Version.akka

    val akkaTestKit          = "com.typesafe.akka" %% "akka-testkit"                      % Version.akka
    val akkaMultiNodeTestKit = "com.typesafe.akka" %% "akka-multi-node-testkit"           % Version.akka

    // --- ALPAKKA ---
    val akkaStreamKafka       = "com.typesafe.akka" %% "akka-stream-kafka"                 % Version.akkaStreamKafka
    val akkaStreamContrib     = "com.typesafe.akka" %% "akka-stream-contrib"               % Version.akkaStreamContrib
    val akkaStreamContribAmqp = "com.typesafe.akka" %% "akka-stream-contrib-amqp"          % Version.akkaStreamContrib
    val akkaStreamContribMqtt = "com.typesafe.akka" %% "akka-stream-contrib-mqtt"          % Version.akkaStreamContrib
    val akkaStreamContribXmlDeps = "com.fasterxml"   % "aalto-xml"                         % "1.0.0" // replace with XML module when published
    // --- ALPAKKA ---

    val akkaSlf4j            = "com.typesafe.akka" %% "akka-slf4j"                    % Version.akka
    val logbackClassic       = "ch.qos.logback"    %  "logback-classic"               % Version.logback
  }
  object Test {
    val scalaTest = "org.scalatest" %% "scalatest"  % "3.0.1" % "test"
    val commonsIo = "commons-io"     % "commons-io" % "2.5"   % "test"
  }

  import Compile._
  private val testing = Seq(Test.scalaTest, Test.commonsIo)
  private val streams = Seq(akkaStream, akkaStreamTestkit)
  private val logging = Seq(akkaSlf4j, logbackClassic)

  val core = Seq(akkaActor, akkaTestKit) ++ streams ++ testing ++ logging
  val engine = Seq(akkaActor) ++ testing ++ logging
  val service = Seq(akkaActor, akkaHttpCore, akkaHttp, akkaHttpSprayJson, akkaHttpXml, akkaHttpTestkit) ++ testing ++ logging
  val alpakka = Seq(akkaStreamKafka, akkaStreamContrib, akkaHttpJacksonJson, akkaHttpSprayJson, akkaStreamContribAmqp, akkaStreamContribMqtt, akkaStreamContribXmlDeps)

  val all = core ++ engine ++ service ++ alpakka
}
