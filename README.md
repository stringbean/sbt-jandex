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
// add the plugin
addSbtPlugin("software.purpledragon" % "sbt-jandex" % "<version>")

// add the Jandex 3.x library
libraryDependencies += "io.smallrye" % "jandex" % "3.1.2"

// or Jandex 2.x library
libraryDependencies += "org.jboss" % "jandex" % "2.4.3.Final"

// or Jandex 1.x library
libraryDependencies += "org.jboss" % "jandex" % "1.2.4.Final"
```

Jandex indexes will automatically be generated and added to the binary JARs.

## Tasks

### `jandex`

Generates Jandex index for the main classes and stores it in `jandexOutput.value / jandex.idx`.

## Settings

### `jandexOutput`

*   **Description:** Directory to store generated Jandex index in.
*   **Accepts:** `File`
*   **Default:** `crossTarget.value / jandex`

### `jandexIncludeInPackage`

*   **Description:** Whether to include Jandex index in the main JAR. If `true` then the index will be included under
    \`META-INF/jandex.idx.
*   **Accepts:** `Boolean`
*   **Default:** `true`

[jandex]: https://smallrye.io/jandex
