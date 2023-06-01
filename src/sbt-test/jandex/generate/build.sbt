name := "jandex-cdi"
scalaVersion := "2.13.10"

libraryDependencies += "jakarta.enterprise" % "jakarta.enterprise.cdi-api" % "4.0.1" % Provided

val verifyJarIndex = taskKey[Unit]("check Jandex index is in JAR")

verifyJarIndex := {
  import scala.util.Using
  import java.util.jar.JarFile

  val projectJar = (Compile / packageBin).value

  Using(new JarFile(projectJar)) { jarFile =>
    val indexEntry = jarFile.getEntry("META-INF/jandex.idx")

    if (indexEntry == null) {
      sys.error("Missing index.idx in JAR")
    }
  }
}