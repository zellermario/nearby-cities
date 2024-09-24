package domain

import repository.naive.UsaStates

case class City(name: String, stateCode: String, coordinate: Coordinate)

object City {

  type ValidationError = String
  
  case class Input(name: String, stateCode: String, latDegrees: String, lonDegrees: String)

  def parseCityFrom(cityInput: City.Input): Either[String,  City] =
    for
      name       <- parseCity(cityInput.name)
      stateCode  <- parseStateCode(cityInput.stateCode)
      coordinate <- Coordinate.parseCoordinateFrom(Coordinate.Input(cityInput.latDegrees, cityInput.lonDegrees))
    yield City(name, stateCode, coordinate)

  private def parseCity(name: String): Either[String, String] = 
    Either.cond(!name.isBlank, name, "City name cannot be empty.")

  private def parseStateCode(stateCode: String): Either[ValidationError, String] =
    Either.cond(UsaStates.stateCodes.contains(stateCode.toUpperCase),
      stateCode.toUpperCase, 
      "State code must be a valid two-letter US state code.")

}