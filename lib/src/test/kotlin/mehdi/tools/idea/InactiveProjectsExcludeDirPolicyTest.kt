package mehdi.tools.idea

import com.intellij.openapi.vfs.findFile
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class InactiveProjectsExcludeDirPolicyTest : BasePlatformTestCase() {

  override fun setUp() {
    super.setUp()
    myFixture.copyFileToProject("settings.gradle.kts")
    myFixture.copyFileToProject("inactive_modules.txt")
    //project.registerExtension(DirectoryIndexExcludePolicy.EP_NAME, InactiveProjectsExcludeDirPolicy(project), myFixture.testRootDisposable)
  }

  fun tests() {
    // I want to test that the expected directories are excluded from the project due to the registered extension.
    // But I haven't gotten to that point yet. I'm just checking to see if the project contains the right files first, and it doesn't.
    assertNotNull(project.baseDir.findFile("inactive_modules.txt")) // This fails
    assertNotNull(myFixture.findFileInTempDir("inactive_modules.txt"))// Interestingly though, the tempDir contains the file
  }

  override fun getTestDataPath(): String = "testData"
}