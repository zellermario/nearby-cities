import repository.naive.NaiveCityRepository
import service.{CityReaderService, NearbyCitiesService}

import scala.io.Source

object Application {

  @main
  def main(): Unit = {
    val cityRepository = NaiveCityRepository()
    val cityParser = CityReaderService()
    val nearbyCitiesService = new NearbyCitiesService(cityParser, cityRepository)

    val csvFileSource = Source.fromResource("cities.csv")
    nearbyCitiesService.loadCitiesFromCsvFile(csvFileSource)
    nearbyCitiesService.findFiveNearbyCitesForEachCity()
  }

}
