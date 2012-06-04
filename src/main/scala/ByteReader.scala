/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/05/21
 * Time: 7:00
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils

import java.io._
import java.nio._
import java.nio.charset.Charset


object ByteReader {

  object MovieFormat extends Enumeration {
    val MOV = Value
    val MP4 = Value
    val MPV = Value
    val ThreeGP = Value
    val Unknown = Value
  }

//  val charset = Charset.forName("MS932")
//
//  var majorBrand = null
//  var buf: Array[Byte] = null
//  var bb: ByteBuffer = null
//
//  def pos: Int = bb.position
//  def isEnd: Boolean = bb.position >= bb.capacity

  def parseFile(path: String) = {
    val movieFile = new File(path)
    val movieBuffer = new Array[Byte](movieFile.length().toInt)
    val inputStream = new FileInputStream(movieFile)
    inputStream.read(movieBuffer)
    inputStream.close()

    movieBuffer
  }


  def briefDetector(movieData: Array[Byte]) = {
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

//  def detectFormat(movieData: Array[Byte]): MovieFormat.Value = {
//
//    buf = movieData.slice(0, 255)
//    bb = ByteBuffer.wrap(movieData).order(ByteOrder.LITTLE_ENDIAN)
//
//    for {i <- 0 to 3} {
//      get()
//    }
//
//    val ftypBox = getString(4)
//    if (ftypBox != "ftyp") {
//      throw new Exception("This file does not have a ftyp box at the head!")
//    }
//
//    // TODO: ここの修正は一旦やめて，Apache Tika について調べる
//    val movieFormat: MovieFormat.Value = checkMajorBrand(getString(4))
//
//    if (movieFormat != MovieFormat.Unknown) {
//      canPlayIniPhone(movieFormat)
//    }
//
//    movieFormat
//  }

  /**
   * iPhoneで撮影すると"qt  "になるようである．
   *
   * MPMoviePlayerController が対応するためには
   * 以下のどちらかの圧縮方式でなければならない．
   *
   * H.264 Baseline Profile Level 3.0 video, up to 640 x 480 at 30 fps.
   * (The Baseline profile does not support B frames.)
   *
   * MPEG-4 Part 2 video (Simple Profile)
   *
   * */
//  def checkMajorBrandOld(brand: String) = {
//    brand match {
//      case "mqt " | "qt  " => MovieFormat.MOV
//      case "mp42" | "mmp4" => MovieFormat.MP4
//      // MPV に相当する ftyp のタイプが不明
//      // case "????" => MovieFormat.MPV
//      case "3gp4" | "3gp5" | "3gp6" => MovieFormat.ThreeGP
//
//      case _ => MovieFormat.Unknown
//    }
//  }


  def checkMajorBrand(brand: String) = {
    brand match {
      case "mqt " | "qt  " => MovieFormat.MOV
      case "mp41" | "mp42" => MovieFormat.MP4
      case "3ge6" | "3ge7" | "3gg6" | "3gp1" | "3gp2" | "3gp3" | "3gp4" | "3gp5" | "3gp6" | "3gs7" => MovieFormat.ThreeGP
      case _ => MovieFormat.Unknown
    }
  }

  ////////////////////////////////////////////////////////
  ///////
  // All types should be 4 bytes long, space padded as needed
//  typesMap.put(MediaType.audio("mp4"), Arrays.asList(
//    "M4A ", "M4B ", "F4A ", "F4B "));
//  typesMap.put(MediaType.video("3gpp"), Arrays.asList(
//    "3ge6", "3ge7", "3gg6", "3gp1", "3gp2", "3gp3", "3gp4", "3gp5", "3gp6", "3gs7"));
//  typesMap.put(MediaType.video("3gpp2"), Arrays.asList(
//    "3g2a", "3g2b", "3g2c"));
//  typesMap.put(MediaType.video("mp4"), Arrays.asList(
//    "mp41", "mp42"));
//  typesMap.put(MediaType.video("x-m4v"), Arrays.asList(
//    "M4V ", "M4VH", "M4VP"));
//
//  typesMap.put(MediaType.video("quicktime"), Collections.<String>emptyList());
//  typesMap.put(MediaType.application("mp4"), Collections.<String>emptyList());



//  def canPlayIniPhone(value: MovieFormat.Value): Boolean = {
//    value match {
//      case MovieFormat.MOV => {
//        // TODO
//        true
//      }
//      // TODO: ここに他のフォーマットでの処理を書く
//      case _ => {
//        // TODO
//        false
//      }
//    }
//  }


//  def getString(length: Int): String = {
//    val ret = new String(buf, bb.position, length, charset)
//    bb.position(bb.position() + length)
//    ret
//  }
//
//  def get(): Int = {
//    val b = bb.get()
//    if (b >= 0) b else b + 256
//  }
//
//  def getTwoByteAsInt(): Int = {
//    val b0 = get()
//    val b1 = get()
//    (b1 << 8) + b0
//  }
//
//  def getFourByteAsInt(): Int = {
//    val b0 = get()
//    val b1 = get()
//    val b2 = get()
//    val b3 = get()
//    (b3 << 24) + (b2 << 16) + (b1 << 8) + b0
//  }
//
//  def getFloat(): Float = bb.getFloat()
}
