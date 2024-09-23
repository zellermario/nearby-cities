package service

import com.github.tototoshi.csv.CSVWriter
import domain.City

import java.io.Writer
import scala.util.Using

class OutputWriterService {

  def writeResultsToCsv(results: Map[City, Seq[City]], writer: Writer): Either[String, Unit] = {
    Using.resource(CSVWriter.open(writer)) { csvWriter =>
      val format = (city: City) => s"${city.name}, ${city.stateCode}"
      val rows = results.toList
        .map((city, neighbors) => (city +: neighbors).map(format))
      Right(csvWriter.writeAll(rows))
    }
  }

}
