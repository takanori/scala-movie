package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {

  def main(args: Array[String]) {

    val path = "/Users/mbp20120411/scala_files/movies/IMG_0467.MOV"
//    val path = "/Users/mbp20120411/scala_files/movies/mov_h264_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mov_mpeg4_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mp4_mpeg4_aac.mp4"


//    val formatDetector = new FormatDetector;
//
//    val movieBuffer = formatDetector.parseFile(path)
//    val format = formatDetector.detectFormat(movieBuffer)
//    println(format)
//
//    formatDetector.PrintDetectionResult(path)


    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'x', 'x', 'x', 'x', 'q', 't', ' ', ' ')

    //      100 must_== 100
    try {
      formatDetector.detectFormat(movieBuffer)
    } catch {
//      case e: Exception => e must_== "This file does not have a ftyp box at the head."
      case e: Exception => print(e)

    }
  }

}
