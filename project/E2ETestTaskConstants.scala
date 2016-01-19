import java.text.SimpleDateFormat
import java.util.Date

import sbt._

object E2ETestTaskConstants {

  val ASYNC_PROCESS_SCRIPTS = Array("startSelenium", "startDatabase", "runTestSetup", "runSvc")

  val PROTRACTOR_TEST_SCRIPT = "runProtractorTests"

  val OUTPUT_LOG_SUFFIX = "Output.log"

  val PORTS = Array("4444", "9092", "9000", "9001")

  val requiredPorts = PORTS.map(p => { p }).foldLeft("")(((a, b) => a + " " + b))

  val ALL_PROCESSES_STARTED = "All Processes Started - Ready For Protractor Tests"

  val PROCESSES_NOT_STARTED = "Protractor Tests cannot be run, dependent processes did not start. Check processes running on the following ports " + requiredPorts

  val MAX_WAIT = 120000

  val RESULTS_FOLDER = "test-e2e-results"

  val WAIT_INTERVAL = 5000

  val PORTS_IN_USE_ERROR = "\nERROR - Cannot Run Protractor tests as there are processes already running on one or more of the required ports" + requiredPorts + ". Stop the processes running on these ports and retry\n"

  val LOGICAL_AND = (a: Boolean, b: Boolean) => a && b

  val LOGICAL_OR = (a: Boolean, b: Boolean) => a || b
}
