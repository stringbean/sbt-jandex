package software.purpledragon.sbt.jandex

import sbt.Keys.*
import sbt.plugins.JvmPlugin
import sbt.{Def, *}

object JandexPlugin extends AutoPlugin {
  override val trigger: PluginTrigger = allRequirements
  override val requires: Plugins = JvmPlugin

  object autoImport {
    lazy val jandex: TaskKey[File] = taskKey[File]("generate Jandex index")
    lazy val jandexOutput = taskKey[File]("output dir")
  }

  import autoImport.*

  override def projectSettings: Seq[Def.Setting[?]] = Seq(
    jandexOutput := crossTarget.value / "jandex",
    jandex := {
      streams.value.log.info("Generating Jandex index")
      val classDirs = (Runtime / products).value

      JandexGenerator.generateIndex(classDirs, jandexOutput.value)
    },
    Compile / packageBin / mappings += {
      jandex.value -> "META-INF/jandex.idx"
    },
  )
}
