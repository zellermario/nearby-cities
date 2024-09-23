import repository.indexed.IndexedCityRepository
import service.{InputReaderService, NearbyCitiesService, OutputWriterService}

import java.io.{File, PrintWriter}
import scala.io.Source

object Application {

  @main
  def main(): Unit = {
    val cityRepository = IndexedCityRepository()
    val inputReader = InputReaderService()
    val outputWriter = OutputWriterService()
    val nearbyCitiesService = NearbyCitiesService(cityRepository)

    val inputFile = Source.fromResource("cities.csv")
    val maybeCities = inputReader.parseCitiesFromCsv(inputFile)

    maybeCities match
      case Left(errors) =>
        errors.foreach(System.err.println)
        System.exit(0)
      case Right(cities) =>
        nearbyCitiesService.loadCities(cities)
        val results = nearbyCitiesService.findNearbyCitesForEachCity(cities, numberOfResults = 5)
        val outputFile = new PrintWriter(new File("neighbors.csv"))
        outputWriter.writeResultsToCsv(results, outputFile)
  }

}
