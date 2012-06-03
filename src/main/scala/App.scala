package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {
  def main(args: Array[String]) {
    println("main started.")

    testWithFilePath("/Users/mbp20120411/scala_files/IMG_0467.MOV")
    testWithFilePath("/Users/mbp20120411/scala_files/sample_mpeg4.mp4")
    testWithFilePath("/Users/mbp20120411/scala_files/sample_iPod.m4v")

  }

  def test = {
    //val movieFile = new File("/Users/mbp20120411/scala_files/IMG_0467.MOV")
    //val movieFile = new File("/Users/mbp20120411/scala_files/sample_mpeg4.mp4")
    val movieFile = new File("/Users/mbp20120411/scala_files/sample_iPod.m4v")

    val testBuffer = new Array[Byte](movieFile.length.toInt)
    val io = new FileInputStream(movieFile)
    io.read(testBuffer)
    io.close()

    FormatDetector.detectFormat(testBuffer)

  }

  def testWithFilePath(path: String) = {
    //val movieFile = new File("/Users/mbp20120411/scala_files/IMG_0467.MOV")
    //val movieFile = new File("/Users/mbp20120411/scala_files/sample_mpeg4.mp4")
    val movieFile = new File(path)

    val testBuffer = new Array[Byte](movieFile.length.toInt)
    val io = new FileInputStream(movieFile)
    io.read(testBuffer)
    io.close()

    FormatDetector.detectFormat(testBuffer)
  }

}
