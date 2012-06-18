/**
 * InformationDetectorTest.scala
 */

import com.takanori.MovieUtils.{MovieFormat, FormatDetector}
import org.specs2.mutable.Specification

class InformationDetectorTest extends Specification {
  "get information of a mov file" in {
    val path = "src/test/resources/sample_iTunes.mov"
    val formatDetector = new FormatDetector
    val movieBuffer = formatDetector.parseFile(path)
    val information = formatDetector.detectMovieInformation(movieBuffer)

    val format = information.format
    val playTime = (information.playTime * 10.0f).floor / 10.0f
    val width = information.width
    val height = information.height

    format must_== MovieFormat.MOV
    playTime must_== 85.5f
    width must_== 640
    height must_== 480
  }
  "get information of a mp4 file" in {
    val path = "src/test/resources/sample_mpeg4.mp4"
    val formatDetector = new FormatDetector
    val movieBuffer = formatDetector.parseFile(path)
    val information = formatDetector.detectMovieInformation(movieBuffer)

    val format = information.format
    val playTime = (information.playTime * 10.0f).floor / 10.0f

    format must_== MovieFormat.MP4
    playTime must_== 4.9f
    // MP4parser can detect width and height of the only MOV files
  }
  "get information of a 3gp file" in {
    val path = "src/test/resources/sample.3gp"
    val formatDetector = new FormatDetector
    val movieBuffer = formatDetector.parseFile(path)
    val information = formatDetector.detectMovieInformation(movieBuffer)

    val format = information.format
    val playTime = (information.playTime * 10.0f).floor / 10.0f

    format must_== MovieFormat.ThreeGP
    playTime must_== 4.9f
    // MP4parser can detect width and height of the only MOV files
  }
  "fail to get information of the file that does not have a 'ftyp' box at the head" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'x', 'x', 'x', 'x', 'q', 't', ' ', ' ', 0x00)

    var exceptionString = "foo"
    try {
      formatDetector.detectMovieInformation(movieBuffer)
    } catch {
      case e: Exception => exceptionString = e.toString
    }
    exceptionString must_== "java.lang.Exception: This file does not have a ftyp box at the head."
  }
  "fail to get information of the file that is not supported" in {
    val formatDetector = new FormatDetector;
    val movieBuffer = Array[Byte](0x00, 0x00, 0x00, 0x00, 'f', 't', 'y', 'p', 'x', 'x', 'x', 'x', 0x00)

    var exceptionString = "foo"
    try {
      formatDetector.detectMovieInformation(movieBuffer)
    } catch {
      case e: Exception => exceptionString = e.toString
    }
    exceptionString must_== "java.lang.Exception: This format is not supported."
  }

}
