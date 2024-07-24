package mehdi.tools.idea

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.impl.DirectoryIndexExcludePolicy
import com.intellij.openapi.vfs.findFile
import com.intellij.openapi.vfs.readText
import java.nio.file.Paths

class InactiveProjectsExcludeDirPolicy (private val project: Project) : DirectoryIndexExcludePolicy {

  override fun getExcludeUrlsForProject(): Array<out String> {
    val inactiveProjectDirs = getInactiveProjectDirs()

    return if (inactiveProjectDirs.isNotEmpty()) {
      logger.info("Excluding inactive project dirs: $inactiveProjectDirs")
      inactiveProjectDirs
        .map { Paths.get(project.basePath, it) }
        .map { "file://$it" }
        .toSet().toTypedArray()
    } else {
      emptyArray()
    }
  }

  private fun getInactiveProjectDirs(): List<String> {
    val inactiveProjectsFile = project.baseDir.findFile("inactive_modules.txt")
    if (inactiveProjectsFile == null) {
      logger.warn("The inactive_modules.txt file is missing")
      return emptyList()
    }
    return inactiveProjectsFile.readText().split("\n")
      .filter { it.trim().isNotEmpty() }
      .map { it.replace(':', '/').trim('/') }
  }

  companion object {
    private val logger = Logger.getInstance(InactiveProjectsExcludeDirPolicy::class.java)
  }
}