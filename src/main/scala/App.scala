package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {

  def main(args: Array[String]) {
    testWithFilePath("/Users/mbp20120411/scala_files/movies/IMG_0467.MOV")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/mov_h264_aac.mov")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/mov_jpeg_aac.mov")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/mov_mpeg4_aac.mov")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/mp4_h264_aac.mp4")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/mp4_mpeg4_aac.mp4")
//    testWithFilePath("/Users/mbp20120411/scala_files/movies/sample_iPod.m4v")
  }

  def testWithFilePath(path: String) = {
    val movieFile = new File(path)

    val testBuffer = new Array[Byte](movieFile.length.toInt)
    val io = new FileInputStream(movieFile)
    io.read(testBuffer)
    io.close()

    val result: (Any, Float, Float, Float) = FormatDetector.detectFormat(testBuffer)

    val format = result._1
    val contentLength = result._2
    val imageWidth = result._3
    val imageLength = result._4

    println(path)
    println("MovifFormat: " + format)
    println("ContentLength: " + contentLength)
    println("ImageWidth: " + imageWidth)
    println("ImageLength: " + imageLength)
  }

}
