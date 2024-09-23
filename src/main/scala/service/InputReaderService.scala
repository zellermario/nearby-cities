package service

import com.github.tototoshi.csv.CSVReader
import domain.City

import scala.io.Source
import scala.util.Using

class InputReaderService {

  private object Columns {
    val name = "city"
    val stateCode = "state_id"
    val latDegrees = "lat"
    val lonDegrees = "lng"
  }

  def parseCitiesFromCsv(inputFile: Source): Either[Seq[String], Seq[City]] =
    Using.resource(CSVReader.open(inputFile)) { csvReader =>
      val results = csvReader.allWithHeaders()
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
