name         := "sbt-jandex"
organization := "software.purpledragon"

enablePlugins(SbtPlugin)

libraryDependencies ++= Seq(
  "io.smallrye" % "jandex" % "3.1.1" % Provided,
)

scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++
    Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false

organizationName := "Michael Stringer"
startYear        := Some(2023)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

developers := List(
  Developer("stringbean", "Michael Stringer", "@the_stringbean", url("https://github.com/stringbean")),
)

homepage  := Some(url("https://github.com/stringbean/sbt-jandex"))
scmInfo   := Some(
  ScmInfo(url("https://github.com/stringbean/sbt-jandex"), "https://github.com/stringbean/sbt-jandex.git"))
publishTo := sonatypePublishToBundle.value

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  releaseStepInputTask(scripted),
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  publishArtifacts,
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges,
)
