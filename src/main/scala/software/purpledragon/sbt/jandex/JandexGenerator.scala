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

import java.io.InputStream
import java.lang.reflect.Method

object JandexGenerator {
  private val indexMethod: Method = {
    // use reflection to locate method as the signature has changed
    // Jandex < 3.0 this returns ClassInfo
    // Jandex > 3.0 this returns void/Unit
    classOf[Indexer].getMethod("index", classOf[InputStream])
  }

  // TODO cache generated index
  def generateIndex(classDirs: Seq[File], outputDir: File): File = {
    val indexer = new Indexer()

    // 1.x & 2.x
    // public org.jboss.jandex.ClassInfo index(java.io.InputStream) throws java.io.IOException;
    // 3.x
    // public void index(java.io.InputStream) throws java.io.IOException;

    val classFiles = classDirs flatMap { dir =>
      (dir ** "*.class").get()
    }

    classFiles foreach { clazz =>
      Using.fileInputStream(clazz) { is =>
        indexMethod.invoke(indexer, is)
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
