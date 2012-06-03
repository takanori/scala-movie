/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/05/28
 * Time: 16:18
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils

import java.io._
import org.apache.tika.parser.mp4.MP4Parser
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.ParseContext

object FormatDetector {

  object MovieFormat extends Enumeration {
    val MOV = Value
    val MP4 = Value
    val MPV = Value
    val ThreeGP = Value
    val Unknown = Value
  }

  val parser = new MP4Parser

  def detectFormat(movieData: Array[Byte]): MovieFormat.Value = {
    val stream = new ByteArrayInputStream(movieData)
    val handler = new BodyContentHandler
    val metadata = new Metadata
    val parseContext = new ParseContext

    try {
      parser.parse(stream, handler, metadata, parseContext)
      println("-----------------")
    } finally {
      stream.close
    }

    print(metadata)

    println("\n\n--------------")

    println("Content-Type = " + metadata.get("Content-Type"))
    println("ImageWidth = " + metadata.get("ImageWidth"))
    println("ImageLength = " + metadata.get("ImageLength"))

    println("=====================================")

//    println("Title = " + metadata.get("title"))
//    println("content = " + handler.toString)

    MovieFormat.Unknown
  }
}
