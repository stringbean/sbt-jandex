{
  val pluginVersion = System.getProperty("plugin.version")
  if (pluginVersion == null)
    throw new RuntimeException("""|The system property 'plugin.version' is not defined.
                                  |Specify this property using the scriptedLaunchOpts -D.""".stripMargin)
  else addSbtPlugin("software.purpledragon" % "sbt-jandex" % pluginVersion)
}

libraryDependencies += "org.jboss" % "jandex" % "2.4.3.Final"
