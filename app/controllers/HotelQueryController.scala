package controllers

import javax.inject.{Inject, Singleton}

import models.Hotel
import play.api.libs.json._
import play.api.mvc._
import services.ReadCSV

@Singleton
class HotelQueryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def queryHotel(sort : Option[String], city : Option[String]) = Action {

    // Initialize Json format for Hotel
    implicit val hotelFormat = Json.writes[Hotel]

    // Get hotel list
    var hotelList = ReadCSV.getHotels

    if (!sort.isEmpty) {
      if (sort.get.equals("asc"))
        hotelList = hotelList.sortWith(_.price < _.price)
      else
        hotelList = hotelList.sortWith(_.price > _.price)
    }

    if (!city.isEmpty)
      hotelList = hotelList.filter(_.city.toLowerCase.startsWith(city.get.toLowerCase()))

    Ok(views.html.hotel(hotelList))
  }

}
