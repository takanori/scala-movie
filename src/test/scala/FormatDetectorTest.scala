/**
 * FormatDetectorTest.scalas
 */

import com.takanori.MovieUtils.{MovieFormat, FormatDetector}
import org.specs2.mutable.Specification

class FormatDetectorTest extends Specification {
  "detect MOV when the ftyp box is 'mqt '" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'm', 'q', 't', ' ')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.MOV
  }
  "detect MOV when the ftyp box is 'qt  '" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'q', 't', ' ', ' ')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.MOV
  }

  "detect MP4 when the ftyp box is 'mp41'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'm', 'p', '4', '1')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.MP4
  }
  "detect MP4 when the ftyp box is 'mp42'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'm', 'p', '4', '2')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.MP4
  }

  "detect ThreeGP when the ftyp box is '3ge6'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'e', '6')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3ge7'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'e', '7')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gg6'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'g', '6')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp1'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'p', '1')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp3'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'p', '3')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp4'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'p', '4')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp5'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'p', '5')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp6'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 'p', '6')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }
  "detect ThreeGP when the ftyp box is '3gp7'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', '3', 'g', 's', '7')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.ThreeGP
  }

  "detect Unknown when the ftyp box is 'xxxx'" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'x', 'x', 'x', 'x')
    formatDetector.detectFormat(movieBuffer) must_== MovieFormat.Unknown
  }

  "fail to detect when the file does not have a ftyp box at the head" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'x', 'x', 'x', 'x', 'q', 't', ' ', ' ')

    var exceptionString = "foo"
    try {
      formatDetector.detectFormat(movieBuffer)
    } catch {
      case e: Exception => exceptionString = e.toString
    }
    exceptionString must_== "java.lang.Exception: This file does not have a ftyp box at the head."
  }
}
