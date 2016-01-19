import java.text.SimpleDateFormat
import java.util.Date

import sbt._

object PAMMConstants {

  val ASYNC_PROCESS_SCRIPTS = Array("startDatabase", "runSvc")

  val OUTPUT_LOG_SUFFIX = "Output.log"

  val PORTS = Array("9092", "9000")

  val ALL_PROCESSES_STARTED = "All Processes Started"

  val PROCESSES_NOT_STARTED = "Application be run, dependent processes did not start. Check processes running on the following ports " + PORTS

  val RESULTS_FOLDER = "svc/logs"

  val requiredPorts = PORTS.map(p => { p }).foldLeft("")(((a, b) => a + " " + b))

  val PORTS_IN_USE_ERROR = "\nERROR - Cannot Run Application as there are processes already running on one or more of the required ports" + requiredPorts + ". Stop the processes running on these ports and retry\n"

  val LOGICAL_OR = (a: Boolean, b: Boolean) => a || b
}
