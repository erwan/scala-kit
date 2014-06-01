import sbt._
import Keys._

object BuildSettings {

  val buildName              = "scala-kit"
  val buildOrganization      = "io.prismic"
  val buildVersion           = Option(System.getProperty("version")).map(_.trim).getOrElse("1.0-SNAPSHOT")

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization    := buildOrganization,
    version         := buildVersion,
    scalaVersion    := "2.11.1",
    crossScalaVersions := Seq("2.11.1", "2.10.4"),
    scalacOptions   := Seq("-deprecation", "-unchecked", "-feature")
  )
}

object KitBuild extends Build {

  lazy val ScalaKit = Project(
    BuildSettings.buildName, file("."),
    settings = BuildSettings.buildSettings ++ Seq(
      scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
      scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/root-doc.txt"),

      resolvers += "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
      resolvers += "Sonatype releases" at "http://oss.sonatype.org/content/repositories/releases",

      libraryDependencies ++= Seq(
        "com.typesafe.play"       %% "play-iteratees"      % "2.3.0",
        "com.typesafe.play"       %% "play-json"           % "2.3.0",
        "com.ning"                %  "async-http-client"   % "1.8.9",
        "commons-collections"     %  "commons-collections" % "3.2.1",
        "org.specs2" %% "specs2" % "2.3.12" % "test"
      )
    )
  )
}
