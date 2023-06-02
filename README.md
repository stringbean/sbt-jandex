# sbt-jandex

[![Build Status](https://img.shields.io/github/actions/workflow/status/stringbean/sbt-jandex/ci.yml?branch=main)](https://github.com/stringbean/sbt-jandex/actions/workflows/ci.yml)
[![Codacy grade](https://img.shields.io/codacy/grade/aad472c1c869488e977a198e97253d8e?label=codacy)](https://app.codacy.com/gh/stringbean/sbt-jandex)
[![Known Vulnerabilities](https://snyk.io/test/github/stringbean/sbt-jandex/badge.svg?targetFile=build.sbt)](https://snyk.io/test/github/stringbean/sbt-jandex?targetFile=build.sbt)
[![sbt-jandex version](https://index.scala-lang.org/stringbean/sbt-jandex/sbt-jandex/latest.svg)](https://index.scala-lang.org/stringbean/sbt-jandex/sbt-jandex) 
[![GitHub Discussions](https://img.shields.io/github/discussions/stringbean/sbt-jandex)](https://github.com/stringbean/sbt-jandex/discussions)


An sbt plugin to generate [Jandex][jandex] indexes for projects.

## Setup

Add the following lines to `project/plugins.sbt`:

```scala
addSbtPlugin("software.purpledragon" % "sbt-jandex" % "<version>")
```

Jandex indexes will automatically be generated and added to the binary JARs.

[jandex]: https://smallrye.io/jandex