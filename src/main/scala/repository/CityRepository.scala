package repository

import domain.City

trait CityRepository {

  def loadCities(cities: Seq[City]): Unit
  
  def getAllCities: Seq[City]

  def findCityByName(name: String): Option[City]

  def findNearestCities(sourceCity: City, numberOfCities: Int = 5): Seq[City]

}
