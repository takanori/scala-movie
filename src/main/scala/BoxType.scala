/**
 * Created with IntelliJ IDEA.
 * User: mbp20120411
 * Date: 12/05/21
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */

package com.takanori.MovieUtils

object BoxType {

  object BoxType extends Enumeration {
    val FTYP = Value
    val MOOV = Value
    /**/ val MVHD = Value
    /**/ val TRAK = Value
    /****/ val TKHD = Value
    /****/ val MDIA = Value
    /******/ val MDHD = Value
    /******/ val HDlR = Value
    /******/ val MINF = Value
    /********/ val VMHD = Value
    /********/ val SMHD = Value
    /********/ val DINF = Value
    /********/ val STBL = Value
    /**********/ val STSD = Value
    /**********/ val STTS = Value
    /**********/ val CTTS = Value
    /**********/ val STSC = Value
    /**********/ val STSZ = Value
    /**********/ val STCO = Value
    /**********/ val CO64 = Value
    /**********/ val STSS = Value
    val MDAT = Value
    val UNKNOWN = Value
  }

  def testShow = {
    println("BoxType.testshow----------------")
  }

}
