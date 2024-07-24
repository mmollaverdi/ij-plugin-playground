import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  alias(libs.plugins.jvm)
  `java-library`
   id("org.jetbrains.intellij.platform")
}

dependencies {
  implementation(libs.kotlinStdLib)
  intellijPlatform {
    intellijIdeaCommunity("2024.1.4")
    pluginVerifier("1.371")
    testFramework(TestFrameworkType.Bundled, "241.18034.82")
  }

  testImplementation(libs.junit)
  // testRuntimeOnly(libs.junitEngine)
  // testRuntimeOnly(libs.junitPlatformLauncher)
}

val pluginName = "mehdi-ij-playground"
val sinceBuildMajorVersion = "231" // corresponds to 2023.1.x versions
val sinceIdeVersion = "2023.1.7"
val untilBuildMajorVersion = "241" // corresponds to 2024.1.x versions
val untilIdeVersion = "2024.1.4"

intellijPlatform {
  buildSearchableOptions = false
  projectName = project.name
  instrumentCode = false
  pluginConfiguration {
    id = "mehdi.ij.plugin.playground"
    name = pluginName
    version = project.version.toString()
    description = "Testing things to do with building an IntelliJ plugin"
    vendor {
      name = "Mehdi"
    }
    ideaVersion {
      sinceBuild = sinceBuildMajorVersion
      untilBuild = "$untilBuildMajorVersion.*"
    }
  }
  verifyPlugin {
    ides {
      ide(IntelliJPlatformType.IntellijIdeaCommunity, sinceIdeVersion)
      ide(IntelliJPlatformType.IntellijIdeaCommunity, untilIdeVersion)
      recommended()
    }
  }
}

intellijPlatformTesting {
  testIde
}

tasks {
  publishPlugin { enabled = false }
  buildPlugin { archiveBaseName = pluginName }
  check { dependsOn("verifyPlugin") }
}

// testing {
//   suites { val test by getting(JvmTestSuite::class) { useJUnitJupiter("5.10.2") } }
// }

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}
