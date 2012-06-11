/**
 * FormatDetector.scala
 */

package com.takanori.MovieUtils

import java.io._
import java.nio.charset.Charset
import java.nio.{ByteOrder, ByteBuffer}

//import org.apache.tika.parser.mp4.MP4Parser
import com.takanori.scalamovie.MP4Parser

import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext

object MovieFormat extends Enumeration {
  val MOV = Value
  val MP4 = Value
  //val MPV = Value
  val ThreeGP = Value
  val Unknown = Value
}

class MovieInformation(movieFormat: MovieFormat.Value, contentLength: Float, imageWidth: Int, imageHeight: Int) {
  var format = movieFormat
  var playTime = contentLength;
  var width = imageWidth;
  var height = imageHeight;
}

class FormatDetector {

  val parser = new MP4Parser

  def parseFile(path: String) = {
    val movieFile = new File(path)
    val movieBuffer = new Array[Byte](movieFile.length().toInt)
    val inputStream = new FileInputStream(movieFile)
    inputStream.read(movieBuffer)
    inputStream.close()

    movieBuffer
  }

  def checkMajorBrand(brand: String) = {
    brand match {
      case "mqt " | "qt  " => MovieFormat.MOV
      case "mp41" | "mp42" => MovieFormat.MP4
      case "3ge6" | "3ge7" | "3gg6" | "3gp1" | "3gp2" |
           "3gp3" | "3gp4" | "3gp5" | "3gp6" | "3gs7" => MovieFormat.ThreeGP
      case _ => MovieFormat.Unknown
    }
  }

  def detectFormat(movieData: Array[Byte]) = {
    val charset = Charset.forName("MS932")
    val smallBuffer = movieData.slice(0, 255)
    val byteBuffer = ByteBuffer.wrap(smallBuffer).order(ByteOrder.LITTLE_ENDIAN)

    def getString(length: Int): String = {
      val retVal = new String(smallBuffer, byteBuffer.position, length, charset)
      byteBuffer.position(byteBuffer.position + length)
      retVal
    }

    def get(): Int = {
      val b = byteBuffer.get()
      if (b >= 0) b else b + 256
    }

    for {i <- 0 to 3} get()

    val ftypBox = getString(4)
    if (ftypBox != "ftyp") {
      throw new Exception("This file does not have a ftyp box at the head.")
    }

    checkMajorBrand(getString(4))
  }

  def detectMovieInformation(movieBuffer: Array[Byte]) = {
    val format = detectFormat(movieBuffer)

    val parserSelector = new ParserSelector

    val parser = parserSelector.selectParser(format) match {
      case Some(f) => f
      case None => throw new Exception("This format is not supported.")
    }

    val stream = new ByteArrayInputStream(movieBuffer)
    val handler = new BodyContentHandler
    val metadata = new Metadata
    val parseContext = new ParseContext

    try {
      parser.parse(stream, handler, metadata, parseContext)
    } finally {
      stream.close
    }

    val contentType: String = metadata.get("Content-Type")
    val contentLength: Float = metadata.get("Content-Length").toFloat
    val imageWidth: Int = metadata.get("tiff:ImageWidth").toInt
    val imageLength: Int = metadata.get("tiff:ImageLength").toInt

    val movieFormat = contentType match {
      case "video/quicktime" => MovieFormat.MOV
      case "application/mp4" | "video/mp4" => MovieFormat.MP4
      case "3gpp" => MovieFormat.ThreeGP
      case _ => MovieFormat.Unknown
    }

    new MovieInformation(movieFormat, contentLength, imageWidth, imageLength)
  }

  def testDetection(path: String) = {
    val movieBuffer = parseFile(path)
    val result = detectMovieInformation(movieBuffer)
    println(result.format)
    println(result.playTime)
    println(result.width)
    println(result.height)
  }

}
