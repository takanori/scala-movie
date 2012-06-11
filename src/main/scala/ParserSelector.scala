/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/06/04
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils


import java.io._
import java.util._

import com.takanori.scalamovie.MP4Parser

import org.apache.tika.metadata.Metadata
import org.apache.tika.parser._
import org.xml.sax.ContentHandler
import org.apache.tika.mime.MediaType


trait ParserTrait extends AbstractParser {

  def parse(stream: InputStream, handler: ContentHandler, metadata: Metadata,
            parseContext: ParseContext)

  def getSupportedTypes(context: ParseContext): Set[MediaType]
}

class TestParser extends ParserTrait {

  override def parse(stream: InputStream, handler: ContentHandler, metadata: Metadata,
            parseContext: ParseContext) = {
    println("TestParser.parse")
  }

  override def getSupportedTypes(context: ParseContext): Set[MediaType] = {
    val set = new HashSet[MediaType]
    set.add(MediaType.video("TEST"))
    set
  }

}

class ParserSelector {

  def selectParser(format: MovieFormat.Value) = {

    format match {
      case MovieFormat.MP4 | MovieFormat.MOV | MovieFormat.ThreeGP => Some(new MP4Parser)
      case _ => None
    }

  }

}
