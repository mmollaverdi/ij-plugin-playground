# ij-plugin-playground

This is to demo and test things to do with building an IntelliJ IDEA plugin. 

Note that the plugin is built using `org.jetbrains.intellij.platform` v2. It currently only registers a `directoryIndexExcludePolicy` extension which reads the list of projects to be excluded from a `inactive_modules.txt` file in the root of the target project.

## Test Failure

The `InactiveProjectsExcludeDirPolicyTest` class aims to apply this plugin to a test project located in `testData`.

The test extends `BasePlatformTestCase` and implements the `getTestDataPath` method. It also manually copies some of the project files over using the `copyFileToProject` method (I've also tried `copyDirectoryToProject`). None of that seems to set up the test project in the expected state.

To run the test, use the following command:
```
./gradlew lib:check
```