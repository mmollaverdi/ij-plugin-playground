import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

rootProject.name = "mehdi-ij-plugin-playground"

plugins {
  id("org.jetbrains.intellij.platform.settings") version "2.0.0-rc1"
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    intellijPlatform {
      defaultRepositories()
    }
  }
}

include("lib")
