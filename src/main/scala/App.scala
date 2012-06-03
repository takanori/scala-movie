package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {
  def main(args: Array[String]) {
    println("main started.")

    val result: (Any, Float, Float, Float) = testWithFilePath("/Users/mbp20120411/scala_files/movies/IMG_0467.MOV")

    val format = result._1
    val contentLength = result._2
    val imageWidth = result._3
    val imageLength = result._4

    println("MovifFormat: " + format)
    println("ContentLength: " + contentLength)
    println("ImageWidth: " + imageWidth)
    println("ImageLength: " + imageLength)
  }

//  def test = {
//    val movieFile = new File("/Users/mbp20120411/scala_files/movies/sample_iPod.m4v")
//
//    val testBuffer = new Array[Byte](movieFile.length.toInt)
//    val io = new FileInputStream(movieFile)
//    io.read(testBuffer)
//    io.close()
//
//    FormatDetector.detectFormat(testBuffer)
//
//  }

  def testWithFilePath(path: String) = {
    val movieFile = new File(path)

    val testBuffer = new Array[Byte](movieFile.length.toInt)
    val io = new FileInputStream(movieFile)
    io.read(testBuffer)
    io.close()

    FormatDetector.detectFormat(testBuffer)
  }

}
