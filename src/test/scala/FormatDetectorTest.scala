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
    "detect MOV" in{

      val formatDetector = new FormatDetector;
      val path = "/Users/mbp20120411/scala_files/movies/IMG_0467.MOV"
      val movieBuffer = formatDetector.parseFile(path)

      //formatDetector.detectFormat(Array[Byte](0x11,0x12)) must_== MovieFormat.MP4
      formatDetector.detectFormat(movieBuffer) must_==  MovieFormat.MOV
    }
    "start with 'Hello'" in {
      "Hello world" must startWith("Hello")
    }

  }


}
