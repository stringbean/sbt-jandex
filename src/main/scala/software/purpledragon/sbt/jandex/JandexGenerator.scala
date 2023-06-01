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

import org.jboss.jandex.{IndexWriter, Indexer}
import sbt.*
import sbt.io.Using

object JandexGenerator {
  // TODO cache generated index
  def generateIndex(classDirs: Seq[File], outputDir: File): File = {
    val indexer = new Indexer()

    val classFiles = classDirs flatMap { dir =>
      (dir ** "*.class").get()
    }

    classFiles foreach { clazz =>
      Using.fileInputStream(clazz) { is =>
        indexer.index(is)
      }
    }

    val index = indexer.complete()

    IO.createDirectory(outputDir)
    val outputFile = outputDir / "jandex.idx"

    Using.fileOutputStream()(outputFile) { os =>
      val writer = new IndexWriter(os)
      writer.write(index)
    }

    outputFile
  }
}
