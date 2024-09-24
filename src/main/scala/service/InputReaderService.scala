package service

import cats.effect.{IO, Resource}
import com.github.tototoshi.csv.CSVReader
import domain.City

import scala.io.Source

class InputReaderService {

  private object Columns {
    val name = "city"
    val stateCode = "state_id"
    val latDegrees = "lat"
    val lonDegrees = "lng"
  }

  def parseCitiesFromCsv(inputFile: Source): IO[Seq[City]] =
    makeCSVReaderResource(inputFile).use(csvReader => parseCities(csvReader))

  private def makeCSVReaderResource(inputFile: Source) = {
    val open = IO(CSVReader.open(inputFile))
    val close = (reader: CSVReader) => IO(reader.close())
    Resource.make(open)(close)
  }

  private def parseCities(reader: CSVReader): IO[Seq[City]] = IO(reader.allWithHeaders())
    .flatMap(rows => {
      rowsToCities(rows) match
        case Left(errors)  => errorsToIO(errors)
        case Right(cities) => IO.pure(cities)
    })

  private def errorsToIO(errors: Seq[String]): IO[Seq[City]] =
    IO.raiseError(new IllegalArgumentException("\n" ++ errors.mkString("\n")))

  private def rowsToCities(rows: List[Map[String, String]]): Either[Seq[String], Seq[City]] = {
    val results = rows
      .zipWithIndex
      .map((row, idx) =>
        rowToInput(row).flatMap(City.parseCityFrom) match
          case Left(error) => Left(s"City cannot be parsed from line ${idx + 1}. $error")
          case Right(city) => Right(city))

    val (errors, cities) = results.partitionMap(identity)
    if errors.isEmpty
      then Right(cities)
      else Left(errors)
  }

  private def rowToInput(row: Map[String, String]): Either[String, City.Input] = {
    val maybeInput =
      for
        name       <- row.get(Columns.name)
        stateCode  <- row.get(Columns.stateCode)
        latDegrees <- row.get(Columns.latDegrees)
        lonDegrees <- row.get(Columns.lonDegrees)
      yield City.Input(name, stateCode, latDegrees, lonDegrees)

    maybeInput.toRight("One or more required columns could not be found.")
  }

}
