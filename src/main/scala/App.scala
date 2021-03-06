package com.takanori.MovieAnalyzer

import java.io._

import com.takanori.MovieUtils._

object App {

  def main(args: Array[String]) {
    val path = "src/test/resources/sample_iTunes.mov"

    val formatDetector = new FormatDetector;
    val movieBuffer = formatDetector.parseFile(path)
    val simpleFormat = formatDetector.detectFormat(movieBuffer)

    println(simpleFormat)

    val information = formatDetector.detectMovieInformation(movieBuffer)
    val format = information.format
    val playTime = (information.playTime * 10.0f).floor / 10.0f
    val width = information.width
    val height = information.height

    println(format)
    println(playTime)
    println(width)
    println(height)
  }
}
