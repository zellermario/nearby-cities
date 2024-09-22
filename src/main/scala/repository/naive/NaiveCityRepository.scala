package repository.naive

import domain.City
import domain.SphericalGeometry.greatCircleDistance
import repository.CityRepository
import squants.space.Length

class NaiveCityRepository extends CityRepository {

  private var citiesByName: Map[String, City] = Map.empty

  override def loadCities(cities: Seq[City]): Unit = {
    citiesByName = cities.foldLeft(Map.empty)((map, city) => map + (city.name -> city))
  }

  override def getAllCities: Seq[City] = citiesByName.values.toSeq

  override def findCityByName(name: String): Option[City] = citiesByName.get(name)
  
  override def findNearestCities(sourceCity: City, numberOfCities: Int = 5): Seq[City] = {
    val cities = citiesByName.values.toSeq
    cities
      .map(targetCity => (targetCity, greatCircleDistance(sourceCity.coordinate, targetCity.coordinate)))
      .sortBy((_, distance) => distance.value)
      .take(numberOfCities)
      .map((targetCity, _) => targetCity)
  }

}

