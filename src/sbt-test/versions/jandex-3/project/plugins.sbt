{
  val pluginVersion = System.getProperty("plugin.version")
  if (pluginVersion == null)
    throw new RuntimeException("""|The system property 'plugin.version' is not defined.
                                  |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
  else addSbtPlugin("software.purpledragon" % "sbt-jandex" % pluginVersion)
}

libraryDependencies += "io.smallrye" % "jandex" % "3.1.2"
