# sbt-jandex

An sbt plugin to generate [Jandex][jandex] indexes for projects.

## Setup

Add the following lines to `project/plugins.sbt`:

```scala
addSbtPlugin("software.purpledragon" % "sbt-jandex" % "<version>")
```

Jandex indexes will automatically be generated and added to the binary JARs.

[jandex]: https://smallrye.io/jandex