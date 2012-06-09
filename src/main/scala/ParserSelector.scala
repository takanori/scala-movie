/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/06/04
 * Time: 17:35
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils

import com.takanori.scalamovie.MP4Parser

class ParserSelector {

  def selectParser(format: MovieFormat.Value) = {

    format match {
      case MovieFormat.MP4 | MovieFormat.MOV | MovieFormat.ThreeGP => Some(new MP4Parser)
      case _ => None
    }

  }
}
