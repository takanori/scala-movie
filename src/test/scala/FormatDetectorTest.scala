import com.takanori.MovieUtils.{MovieFormat, FormatDetector}
import org.specs2.mutable.{Specification, SpecificationWithJUnit}

/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/06/11
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */

class FormatDetectorTest extends Specification {

  "detectMovieInformation" should{
    "detect MP4" in{

      val formatDetector = new FormatDetector;

      formatDetector.detectFormat(Array[Byte](0x11,0x12)) must_== MovieFormat.MP4

    }
    "fail to detect" in{

      val formatDetector = new FormatDetector;
      formatDetector.detectFormat(Array[Byte](0x11,0x12)) must_== MovieFormat.Unknown

    }
  }



}
