name := "jandex-cdi"
scalaVersion := "2.13.10"

libraryDependencies += "jakarta.enterprise" % "jakarta.enterprise.cdi-api" % "4.0.1" % Provided

jandexIncludeInPackage := false

val verifyJarNoIndex = taskKey[Unit]("check Jandex index is not in JAR")

verifyJarNoIndex := {
  import scala.util.Using
  import java.util.jar.JarFile

  val projectJar = (Compile / packageBin).value

  val jarFile = new JarFile(projectJar)
  val indexEntry = jarFile.getEntry("META-INF/jandex.idx")

  if (indexEntry != null) {
    sys.error("jandex.idx found in JAR")
  }
}
