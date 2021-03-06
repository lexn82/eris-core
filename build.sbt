lazy val publishSettings = Seq(
  bintrayOrganization := Some("pagerduty"),
  bintrayRepository := "oss-maven",
  licenses += ("BSD New", url("https://opensource.org/licenses/BSD-3-Clause")),
  publishMavenStyle := true,
  pomExtra := (
    <url>https://github.com/PagerDuty/eris-core</url>
      <scm>
        <url>git@github.com:PagerDuty/eris-core.git</url>
        <connection>scm:git:git@github.com:PagerDuty/eris-core.git</connection>
      </scm>
      <developers>
        <developer>
          <id>lexn82</id>
          <name>Aleksey Nikiforov</name>
          <url>https://github.com/lexn82</url>
        </developer>
      </developers>)
)

lazy val sharedSettings = Seq(
  organization := "com.pagerduty",
  scalaVersion := "2.10.4",
  version := "2.0.4",
  crossScalaVersions := Seq("2.10.4", "2.11.7"),
  libraryDependencies ++= Seq(
    "com.netflix.astyanax" % "astyanax-cassandra" % "3.6.0",
    "com.netflix.astyanax" % "astyanax-core" % "3.6.0",
    "com.netflix.astyanax" % "astyanax-thrift" % "3.6.0"
  )
)
lazy val tests = (project in file("tests")).
  dependsOn(testSupport, main).
  configs(IntegrationTest extend Test).
  settings(Defaults.itSettings: _*).
  settings(sharedSettings: _*).
  settings(
    name := "eris-core-tests",
    publishArtifact in Compile := false,
    publishArtifact in Test := false,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.4" % "it,test",
      "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "it,test",
      "org.scalacheck" %% "scalacheck" % "1.12.2" % "it,test")
    )

lazy val testSupport = (project in file("test-support")).
  dependsOn(main).
  settings(sharedSettings: _*).
  settings(publishSettings: _*).
  settings(
    name := "eris-core-test-support"
  )


lazy val main = (project in file("main")).
  settings(sharedSettings: _*).
  settings(publishSettings: _*).
  settings(
    name := "eris-core",
    libraryDependencies ++= Seq(
      "com.google.guava" % "guava" % "18.0",
      "org.apache.thrift" % "libthrift" % "0.9.1",
      "com.eaio.uuid" % "uuid" % "3.2",
      "org.apache.cassandra" % "cassandra-all" % "2.0.12",
      "org.apache.cassandra" % "cassandra-thrift" % "2.0.12",
      "org.slf4j" % "slf4j-api" % "1.7.12").map(
      _.exclude("org.slf4j", "slf4j-log4j12")).map(
      _.exclude("log4j", "log4j")).map(
      _.exclude("junit", "junit"))
  )

