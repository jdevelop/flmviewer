package com.jdevelop.ubs.flmviewer.data

import java.io.InputStream
trait MessageFieldValueProvider {

  val encoding = "latin1"

  def loadValues(enumName: String, data: InputStream): Array[MessageField] = {
    val values = Class.forName(enumName).getMethod("values").invoke(null);
    val sizeMethod = Class.forName(enumName).getMethod("getSize");
    val result = values match {
      case v: Array[Enum[_]] =>
        for (
          item <- v;
          val size = sizeMethod.invoke(v).asInstanceOf[Int];
          dataStr = new Array[Byte](size);
          val strLen = data.read(dataStr);
          if strLen > 0
        ) yield new MessageField(item.name, new String(dataStr, 0, strLen, encoding));
    }
    return result;
  }

}