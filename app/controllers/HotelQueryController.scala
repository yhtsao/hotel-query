package controllers

import javax.inject.{Inject, Singleton}

import io.ReadCSV
import models.Hotel
import play.api.libs.json._
import play.api.mvc._


@Singleton
class HotelQueryController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def queryHotel = Action {
    implicit val hotelFormat = Json.writes[Hotel]
    val hotelList = ReadCSV.getHotels

    Ok(views.html.hotel(hotelList))
  }

}
