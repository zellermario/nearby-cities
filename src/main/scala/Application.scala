import repository.indexed.IndexedCityRepository
import repository.naive.NaiveCityRepository
import service.{CityReaderService, NearbyCitiesService}

import scala.io.Source

object Application {

  @main
  def main(): Unit = {
    val cityRepository = IndexedCityRepository()
    val cityParser = CityReaderService()
    val nearbyCitiesService = new NearbyCitiesService(cityParser, cityRepository)

    val csvFileSource = Source.fromResource("cities.csv")
    nearbyCitiesService.loadCitiesFromCsvFile(csvFileSource)
    val startedAt = System.currentTimeMillis()
    val result = nearbyCitiesService.findFiveNearbyCitesForEachCity()
    val finishedAt = System.currentTimeMillis()
    val elapsedSeconds = (finishedAt - startedAt) / 1000
    println(s"Finished in $elapsedSeconds seconds.")
    println(result)
    println(s"Finished in $elapsedSeconds seconds.")
  }

}
