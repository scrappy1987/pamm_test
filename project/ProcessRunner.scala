import java.io.File
import java.util.Date

import sbt._
import scala.sys.process.Process
import java.io._

abstract class ProcessRunner extends Build {

  def startAsyncProcesses(asyncProcesses: Array[String]) = {
    asyncProcesses.foreach(f => {
      startAsyncProcess(f, f + ProcessRunnerConstants.OUTPUT_LOG_SUFFIX)
    })
  }

  private def startAsyncProcess(commandFile: String, outputFile: String) = {
    (Process(getDerivedFileName(commandFile)) #>> new File(getOutputFile(outputFile))).run()
  }

  def stopAsyncProcesses(ports: Array[String]) = {
    ports.foreach(f => {
      stopAsyncProcess(f, ProcessRunnerConstants.STOP_PROCESS_SCRIPT + ProcessRunnerConstants.OUTPUT_LOG_SUFFIX)
    })
  }

  private def stopAsyncProcess(processPort: String, outputFile: String): Unit = {
    startSyncProcessWithArgs(ProcessRunnerConstants.STOP_PROCESS_SCRIPT, processPort, outputFile, false)
  }

  def startSyncProcessNoArgs(commandFile: String, outputFile: String, toStdOut: Boolean) = {
    startAsyncProcess(getDerivedFileName(commandFile), outputFile, toStdOut)
  }

  def startSyncProcessWithArgs(commandFile: String, parms: String, outputFile: String, toStdOut: Boolean) = {
    startAsyncProcess(getDerivedFileName(commandFile) + " " + parms, outputFile, toStdOut)
  }

  def startAsyncProcess(command: String, outputFile: String, toStdOut: Boolean) = {
    val file = new File(getOutputFile(outputFile))
    (Process(command) #>> file).!
    if (toStdOut) streamToStdOut(file)
  }

  private def streamToStdOut(file: File) = {
    val fr = new FileReader(file)
    new BufferedReader(fr).lines().toArray.foreach(l => {
      System.out.println(l)
    })
  }

  def checkAsyncProcessesRunning(ports: Array[String], f: (Boolean, Boolean) => Boolean, initialFoldValue: Boolean): Boolean = {
    ports.map(p => {
      checkAsyncProcess(p)
    }).foldLeft(initialFoldValue)((a, b) => f(a, b))
  }

  private def checkAsyncProcess(port: String): Boolean = {
    val pb = Process(getDerivedFileName(ProcessRunnerConstants.CHECK_PROCESS_SCRIPT))
    val baos = new ByteArrayOutputStream
    val code = (pb #> baos).!
    var processRunning = false
    if (baos.toString.contains(ProcessRunnerConstants.LOCAL_ADDRESS + port)) processRunning = true
    processRunning
  }

  private def getOutputFile(outputFileName: String): String = {
    val file = new File(getOutputFolder() + File.separatorChar + outputFileName)
    if (!file.exists()) file.createNewFile()
    file.getAbsolutePath
  }

  private def getOutputFolder(): String = {
    val testDir = new File(getResultsFolder() + File.separatorChar + ProcessRunnerConstants.TEST_RUNTIME)
    if (!testDir.exists()) testDir.mkdirs()
    testDir.getAbsolutePath
  }

  private def getDerivedFileName(fileName: String): String = {
    ProcessRunnerConstants.SCRIPT_FOLDER + fileName + ProcessRunnerConstants.SCRIPT_EXTENSION
  }

  def getResultsFolder() : String
}
