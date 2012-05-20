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

class ByteReader() {

  val CP932 = Charset.forName("MS932")

  val path = new File("/Users/mbp20120411/scala_files/IMG_0619.MOV")

  val buf = new Array[Byte](path.length.asInstanceOf[Int])
  val io = new FileInputStream(path)
  io.read(buf)
  io.close()

  val bb = ByteBuffer.wrap(buf).order(ByteOrder.LITTLE_ENDIAN)

  def pos: Int = bb.position

  def isEnd: Boolean = bb.position >= bb.capacity

  def testShow = {
    println(buf)
    println(bb)
    println(io)
  }

  def getString(length: Int): String = {
    val ret = new String(buf, bb.position, length, CP932)
    bb.position(bb.position() + length)
    ret
  }

  def get(): Int = {
    val b = bb.get()
    if (b >= 0) b else b + 256
  }

  def getWORD(): Int = {
    val b0 = get()
    val b1 = get()
    (b1 << 8) + b0
  }

  def getDWORD(): Int = {
    val b0 = get()
    val b1 = get()
    val b2 = get()
    val b3 = get()
    (b3 << 24) + (b2 << 16) + (b1 << 8) + b0
  }

  def getFloat(): Float = bb.getFloat()
}
