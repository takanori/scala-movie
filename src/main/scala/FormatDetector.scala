/**
 * FormatDetector.scala
 */

package com.takanori.MovieUtils

import java.io._

//import org.apache.tika.parser.mp4.MP4Parser
import com.takanori.scalamovie.MP4Parser

import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext

object FormatDetector {

  object MovieFormat extends Enumeration {
    val MOV = Value
    val MP4 = Value
    //val MPV = Value
    val ThreeGP = Value
    val Unknown = Value
  }

  val parser = new MP4Parser

  def detectFormat(movieData: Array[Byte]): (MovieFormat.Value, Float, Float, Float) = {
    val stream = new ByteArrayInputStream(movieData)
    val handler = new BodyContentHandler
    val metadata = new Metadata
    val parseContext = new ParseContext

    try {
      parser.parse(stream, handler, metadata, parseContext)
    } finally {
      stream.close
    }

    val contentType: String = metadata.get("Content-Type")
    val contentLength: String = metadata.get("Content-Length")
    val imageWidth: String = metadata.get("tiff:ImageWidth")
    val imageLength: String = metadata.get("tiff:ImageLength")

    val movieFormat = contentType match {
      case "video/quicktime" => MovieFormat.MOV
      case "application/mp4" | "video/mp4" => MovieFormat.MP4
      case "3gpp" => MovieFormat.ThreeGP
      case _ => MovieFormat.Unknown
    }

    (movieFormat, contentLength.toFloat, imageWidth.toInt, imageLength.toInt)
  }



}
