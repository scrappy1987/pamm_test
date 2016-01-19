import java.io.File
import java.util.Date

import sbt._
import scala.sys.process.Process
import java.io._

object PAMMStart extends ProcessRunner {
  lazy val startPAMM = taskKey[Unit]("startPAMM")

  def startPAMMTask = {
    if (!checkAsyncProcessesRunning(PAMMConstants.PORTS, PAMMConstants.LOGICAL_OR, false)) {
      startAsyncProcesses(PAMMConstants.ASYNC_PROCESS_SCRIPTS)
    }
    else {
      System.out.println(PAMMConstants.PORTS_IN_USE_ERROR);
    }
  }

  def getResultsFolder() = {
    PAMMConstants.RESULTS_FOLDER
  }
}
