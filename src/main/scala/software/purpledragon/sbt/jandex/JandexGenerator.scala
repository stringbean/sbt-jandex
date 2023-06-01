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
