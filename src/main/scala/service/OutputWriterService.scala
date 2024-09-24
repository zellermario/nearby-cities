package service

import cats.effect.{IO, Resource}
import com.github.tototoshi.csv.CSVWriter
import domain.City

import java.io.Writer

class OutputWriterService {
  
  def writeResultsToCsv(nearbyCityMap: Map[City, Seq[City]], writer: Writer): IO[Unit] = {
    makeCSVWriterResource(writer).use(csvWriter => {
      val format = (city: City) => s"${city.name}, ${city.stateCode}"
      val rows = nearbyCityMap.toList.map((city, neighbors) => (city +: neighbors).map(format))
      IO(csvWriter.writeAll(rows))
    })
  }

  private def makeCSVWriterResource(writer: Writer) = {
    val open = IO(CSVWriter.open(writer))
    val close = (writer: CSVWriter) => IO(writer.close())
    Resource.make(open)(close)
  }

}
