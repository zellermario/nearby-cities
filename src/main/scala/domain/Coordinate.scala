package domain

import domain.City.ValidationError
import squants.space.{Angle, Degrees}

case class Coordinate(latitude: Angle, longitude: Angle)

object Coordinate {

  type ValidationError = String

  case class Input(latDegrees: String, lonDegrees: String)

  def parseCoordinateFrom(coordinateInput: Coordinate.Input): Either[ValidationError, Coordinate] =
    for
      latAsDouble <- parseDouble(coordinateInput.latDegrees)
      lonAsDouble <- parseDouble(coordinateInput.lonDegrees)
      latitude    <- parseLatitude(latAsDouble)
      longitude   <- parseLongitude(lonAsDouble)
    yield Coordinate(latitude, longitude)

  private def parseDouble(doubleString: String): Either[ValidationError, Double] =
    doubleString.toDoubleOption match
      case Some(double) => Right(double)
      case None => Left("Coordinate must be given as a valid floating point number.")

  private def parseLatitude(latDegrees: Double): Either[ValidationError, Angle] =
    Either.cond(latDegrees >= -90.0 && latDegrees <= 90.0,
      Degrees(latDegrees),
      "Latitude must be given in degrees and must be between -90° and +90°.")

  private def parseLongitude(lonDegrees: Double): Either[ValidationError, Angle] =
    Either.cond(lonDegrees >= -180.0 && lonDegrees <= 180.0,
      Degrees(lonDegrees),
      "Longitude must be given in degrees and must be between -180° and +180°.")

}