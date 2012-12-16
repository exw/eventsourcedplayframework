import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "play-blog"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "redis.clients" % "jedis" % "2.1.0",
    "com.yammer.metrics" %% "metrics-scala" % "2.1.2",
    "com.lambdaworks" % "scrypt" % "1.3.3",
    "org.specs2" %% "specs2" % "1.12.3" % "test",
    "org.scalacheck" %% "scalacheck" % "1.9" % "test")

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    routesImport ++= Seq("events._", "eventstore.{ StoreRevision, StreamRevision }", "support.Binders._"),

    templatesImport ++= Seq("events._", "eventstore.{ StoreRevision, StreamRevision }"),

    lessEntryPoints <<= (sourceDirectory in Compile)(base => (
      (base / "assets" / "stylesheets" / "bootstrap" / "bootstrap.less") +++
      (base / "assets" / "stylesheets" / "bootstrap" / "responsive.less"))))

}
