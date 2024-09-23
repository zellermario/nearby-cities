package service

import domain.City
import repository.CityRepository

class NearbyCitiesService(repository: CityRepository) {
  
  def loadCities(cities: Seq[City]): Unit = repository.loadCities(cities)

  def findNearbyCitesForEachCity(cities: Seq[City], numberOfResults: Int): Map[City, Seq[City]] = {
    cities
      .map(sourceCity => (sourceCity, repository.findNearestCities(sourceCity, numberOfResults)))
      .toMap
  }

}
