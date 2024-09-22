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

  def findFiveNearbyCitesForEachCity(): Unit = {
    val allCities = repository.getAllCities
    allCities.zipWithIndex.foreach((sourceCity, idx) => {
      val neighborsPrinted = repository
        .findNearestCities(sourceCity)
        .map(city => s"${city.name}, ${city.stateCode}")
        .mkString(", ")
      println(s"$idx. Neighbors of ${sourceCity.name}, ${sourceCity.stateCode}: $neighborsPrinted")
    })
  }

}
