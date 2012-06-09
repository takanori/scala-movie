/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/06/04
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils

import com.takanori.scalamovie.MP4Parser
import java.io._
import org.apache.tika.sax.BodyContentHandler
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser._
import org.xml.sax.ContentHandler


trait ParserTrait extends AbstractParser {

  def parse(stream: InputStream, handler: ContentHandler, metadata: Metadata,
            parseContext: ParseContext)

  // TODO ここでAbstractParserの関数をすべて実装しておく

}

//class TestParser extends ParserTrait {
//
//  def parse(stream: InputStream, handler: ContentHandler, metadata: Metadata,
//            parseContext: ParseContext) = {
//    println("TestParser.parse")
//  }
//
//}

class ParserSelector {

  def selectParser(format: MovieFormat.Value) = {

    format match {
      case MovieFormat.MP4 | MovieFormat.MOV | MovieFormat.ThreeGP => Some(new MP4Parser)
//      case MovieFormat.Unknown => Some(new TestParser)
      case _ => None
    }

  }
}
