package repository.naive

import domain.Coordinate
import squants.space.{Kilometers, Length}

object SphericalGeometry {

  import math.{acos, cos, sin}

  private val earthAverageRadius = Kilometers(6371)

  def greatCircleDistance(a: Coordinate, b: Coordinate): Length = {
    val longitudeDifference = (a.longitude - b.longitude).toRadians
    val aLatitude = a.latitude.toRadians
    val bLatitude = b.latitude.toRadians
    val centralAngle = acos(
        sin(aLatitude) * sin(bLatitude) +
        cos(aLatitude) * cos(bLatitude) * cos(longitudeDifference))
    earthAverageRadius * centralAngle
  }

}
