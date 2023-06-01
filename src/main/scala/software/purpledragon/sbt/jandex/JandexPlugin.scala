/*
 * Copyright 2023 Michael Stringer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
