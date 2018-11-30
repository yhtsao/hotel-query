package models

import scala.util.Try

case class Hotel(city : String, hotelId : Int, room : String, price : Int) {
  override def toString = s"${city},${hotelId},${room},${price}"
}

object Hotel {
  def parse(s: String): Try[Hotel] = {
    val Array(v1,v2,v3,v4, _*) = s.split(",")
    Try(Hotel(v1, v2.toInt, v3, v4.toInt))
  }
}
