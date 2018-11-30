package services

import models.Hotel

import scala.io.Source
import scala.util.{Failure, Success}

object ReadCSV {

  private var hotelList : List[Hotel] = List()

  def main(args : Array[String]): Unit = {
    getHotels.foreach(println)
  }

  def getHotels() : List[Hotel] = {
    if (hotelList.isEmpty)
      readHotels()
    return hotelList
  }

  def readHotels(): List[Hotel] = {
    println("READ HOTEL")
    val src = Source.fromFile("resources/hoteldb.csv")
    val iter = src.getLines()
    while (iter.hasNext) {
      val row = iter.next();
      Hotel.parse(row) match {
        case Success(hotel) => hotelList :::= List(hotel)
        case Failure(f) => println(f)
      }
    }
    src.close()
    return hotelList
  }
}
