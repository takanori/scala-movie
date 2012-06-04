package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {

  def main(args: Array[String]) {

//    val path = "/Users/mbp20120411/scala_files/movies/IMG_0467.MOV"
    val path = "/Users/mbp20120411/scala_files/movies/mov_h264_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mov_mpeg4_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mp4_mpeg4_aac.mp4"

    testWithFilePath(path)

    println("-----------briefDetector test")

    testBriefDetector(path)
  }

  def testBriefDetector(path: String) = {
    val movieBuffer = FormatDetector.parseFile(path)
    val result = FormatDetector.detectFormat(movieBuffer)
    println(result)
  }

  def testWithFilePath(path: String) = {
    val movieFile = new File(path)

    val testBuffer = new Array[Byte](movieFile.length.toInt)
    val io = new FileInputStream(movieFile)
    io.read(testBuffer)
    io.close()

    val result = FormatDetector.detectMovieData(testBuffer)

    val format = result.format
    val contentLength = result.playTime
    val imageWidth = result.width
    val imageLength = result.height

    println(path)
    println("MovifFormat: " + format)
    println("ContentLength: " + contentLength)
    println("ImageWidth: " + imageWidth)
    println("ImageLength: " + imageLength)
  }

}
