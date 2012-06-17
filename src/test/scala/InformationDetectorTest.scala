/**
 * InformationDetectorTest.scala
 */

import com.takanori.MovieUtils.{MovieFormat, FormatDetector}
import org.specs2.mutable.Specification

class InformationDetectorTest extends Specification {
  "get information of a mov file" in {
    val path = "src/test/resources/mov_h264_aac.mov"
    val formatDetector = new FormatDetector;
    val movieBuffer = formatDetector.parseFile(path)
    val information = formatDetector.detectMovieInformation(movieBuffer)

    val format = information.format
    val playTime = (information.playTime * 10.0f).floor / 10.0f
    val width = information.width
    val height = information.height

    format must_== MovieFormat.MOV
    playTime must_== 21.4f
    width must_== 320
    height must_== 240
  }
}
