import java.io.File
import java.util.Date

import sbt._
import scala.sys.process.Process
import java.io._

object PAMMStop extends ProcessRunner {
  lazy val stopPAMM = taskKey[Unit]("stopPAMM")

  def stopPAMMTask = {
    stopAsyncProcesses(PAMMConstants.PORTS)
  }

  def getResultsFolder() = {
    PAMMConstants.RESULTS_FOLDER
  }
}
