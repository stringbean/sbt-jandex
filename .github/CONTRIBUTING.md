# Contributing

Welcome and thanks for taking the time to contribute to this project!

## Building & Preparing PRs

Before submitting a PR please ensure that you have run the following sbt tasks:

* `test`
* `scripted`
* `scalafmt`
* `headerCheck`

This should ensure that everything works and meets the styleguide.

PRs will get automatically built by [GitHub Actions](https://github.com/stringbean/sbt-jandex/actions) - please make
sure the build passes before marking as ready.
