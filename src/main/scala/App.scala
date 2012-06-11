package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {

  def main(args: Array[String]) {

    val path = "/Users/mbp20120411/scala_files/movies/IMG_0467.MOV"
//    val path = "/Users/mbp20120411/scala_files/movies/mov_h264_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mov_mpeg4_aac.mov"
//    val path = "/Users/mbp20120411/scala_files/movies/mp4_mpeg4_aac.mp4"

    val formatDetector = new FormatDetector;

    formatDetector.testDetection(path)
  }

}
