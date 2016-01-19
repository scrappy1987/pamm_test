import java.text.SimpleDateFormat
import java.util.Date

import sbt._

object ProcessRunnerConstants {
  val FOLDER_TIMESTAMP_FORMAT = "yyyy-MM-dd-HH.mm.ss.SSS"

  val TEST_RUNTIME = getProcessRuntime()

  val WINDOWS_OS = "Windows"
  
  val WINDOWS_SCRIPTS_FOLDER = "buildscripts/batch/"
  
  val WINDOWS_SCRIPT_EXTENSION = ".bat"
  
  val LINUX_SCRIPTS_FOLDER = "buildscripts/shell/"
  
  val LINUX_SCRIPTS_EXTENSION = ".sh"

  val LOCAL_ADDRESS = "0.0.0.0:"

  val OPERATING_SYSTEM = System.getProperty("os.name")

  val SCRIPT_EXTENSION = getScriptExtension()

  val SCRIPT_FOLDER = getScriptFolder()

  val ASYNC_PROCESS_SCRIPTS = Array("startSelenium", "startDatabase", "runTestSetup", "runSvc")

  val CHECK_PROCESS_SCRIPT = "checkProcess"

  val STOP_PROCESS_SCRIPT = "killProcess"

  val PROTRACTOR_TEST_SCRIPT = "runProtractorTests"

  val OUTPUT_LOG_SUFFIX = "Output.log"

  def getScriptExtension(): String = {
    if (OPERATING_SYSTEM.contains(ProcessRunnerConstants.WINDOWS_OS)) WINDOWS_SCRIPT_EXTENSION else LINUX_SCRIPTS_EXTENSION
  }

  def getScriptFolder(): String = {
    if (OPERATING_SYSTEM.contains(WINDOWS_OS)) WINDOWS_SCRIPTS_FOLDER else LINUX_SCRIPTS_FOLDER
  }

  def getProcessRuntime(): String = {
    val sdfDate = new SimpleDateFormat(FOLDER_TIMESTAMP_FORMAT);
    sdfDate.format(new Date())
  }
}
