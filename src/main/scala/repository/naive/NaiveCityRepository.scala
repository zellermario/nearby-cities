package repository.naive

import domain.City
import SphericalGeometry.greatCircleDistance
import repository.CityRepository

class NaiveCityRepository extends CityRepository {

  private var cities: Seq[City] = Seq.empty

  override def loadCities(cities: Seq[City]): Unit = this.cities = cities

  override def getAllCities: Seq[City] = cities

  override def findCity(name: String, stateCode: String): Option[City] =
    cities.find(c => c.name == name && c.stateCode == stateCode)

  override def findNearestCities(sourceCity: City, numberOfCities: Int = 5): Seq[City] = {
    val nearbyStates = UsaStates.adjacentStates.getOrElse(sourceCity.stateCode, Seq(sourceCity.stateCode))
    cities
      .filter(targetCity => sourceCity != targetCity && nearbyStates.contains(targetCity.stateCode))
      .map(targetCity => (targetCity, greatCircleDistance(sourceCity.coordinate, targetCity.coordinate)))
      .sortBy((_, distance) => distance.value)
      .take(numberOfCities)
      .map((targetCity, _) => targetCity)
  }

}

