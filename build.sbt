name         := "sbt-jandex"
organization := "software.purpledragon"

enablePlugins(SbtPlugin)

libraryDependencies ++= Seq(
  "io.smallrye" % "jandex" % "3.1.1",
)

scriptedLaunchOpts := {
  scriptedLaunchOpts.value ++
    Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
}

scriptedBufferLog := false
