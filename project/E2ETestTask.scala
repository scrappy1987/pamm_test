import java.io.File
import java.util.Date

import sbt._
import scala.sys.process.Process
import java.io._

object E2ETestTask extends ProcessRunner {
  lazy val endToEndTest = taskKey[Unit]("endToEndTest")

  def endToEndTestTask = {
    if (!checkAsyncProcessesRunning(E2ETestTaskConstants.PORTS, E2ETestTaskConstants.LOGICAL_OR, false)) {
      try {
        startAsyncProcesses(E2ETestTaskConstants.ASYNC_PROCESS_SCRIPTS)
        if (readyForTest(E2ETestTaskConstants.PORTS)) {
          startSyncProcessNoArgs(E2ETestTaskConstants.PROTRACTOR_TEST_SCRIPT, E2ETestTaskConstants.PROTRACTOR_TEST_SCRIPT + E2ETestTaskConstants.OUTPUT_LOG_SUFFIX, true)
        }
      }
      finally {
        stopAsyncProcesses(E2ETestTaskConstants.PORTS)
      }
    }
    else {
      System.out.println(E2ETestTaskConstants.PORTS_IN_USE_ERROR);
    }
  }

  def readyForTest(ports: Array[String]): Boolean = {
    val startTime = new Date().getTime
    var readyForTest = false
    while ((new Date().getTime - startTime < E2ETestTaskConstants.MAX_WAIT) && !readyForTest) {
      if (checkAsyncProcessesRunning(ports, E2ETestTaskConstants.LOGICAL_AND, true)) readyForTest = true
      Thread.sleep(E2ETestTaskConstants.WAIT_INTERVAL)
    }
    printReadyForTestStatus(readyForTest)
    readyForTest
  }

  def printReadyForTestStatus(readyForTest: Boolean) = {
    if (readyForTest) System.out.println(E2ETestTaskConstants.ALL_PROCESSES_STARTED) else error(E2ETestTaskConstants.PROCESSES_NOT_STARTED)
  }

  def getResultsFolder() = {
    E2ETestTaskConstants.RESULTS_FOLDER
  }
}
