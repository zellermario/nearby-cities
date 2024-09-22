package service

import domain.City
import repository.CityRepository

import scala.io.Source

class NearbyCitiesService(readerService: CityReaderService, repository: CityRepository) {

  def loadCitiesFromCsvFile(csvFileSource: Source): Unit = {
    val maybeCities = readerService.parseCitiesFromFile(csvFileSource)
    maybeCities match
      case Left(errors) => println(errors)
      case Right(cities) => repository.loadCities(cities)
  }

  def findFiveNearbyCitesForEachCity(): Map[City, Seq[City]] = {
    val allCities = repository.getAllCities
    allCities.map(sourceCity => (sourceCity, repository.findNearestCities(sourceCity))).toMap
  }

}
