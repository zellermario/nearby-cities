import cats.effect.{ExitCode, IO, IOApp}
import repository.indexed.IndexedCityRepository
import service.{InputReaderService, NearbyCitiesService, OutputWriterService}

import java.io.{File, PrintWriter}
import scala.io.Source

object Application extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    val cityRepository = IndexedCityRepository()
    val inputReader = InputReaderService()
    val outputWriter = OutputWriterService()
    val nearbyCitiesService = NearbyCitiesService(cityRepository)

    val inputFile = Source.fromResource("cities.csv")
    val outputFile = new PrintWriter(new File("neighbors.csv"))
    
    for {
      cities        <- inputReader.parseCitiesFromCsv(inputFile)
      _             <- IO(nearbyCitiesService.loadCities(cities))
      nearbyCityMap <- IO.pure(nearbyCitiesService.findNearbyCitesForEachCity(cities, numberOfResults = 5))
      _             <- outputWriter.writeResultsToCsv(nearbyCityMap, outputFile)
    } yield ExitCode.Success
  }

}
