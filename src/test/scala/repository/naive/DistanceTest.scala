package repository.naive

import domain.{City, Coordinate}
import repository.naive.SphericalGeometry.greatCircleDistance
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.should
import squants.Percent
import squants.space.{Degrees, Kilometers, Length}

class DistanceTest extends AnyFlatSpec with CustomMatchers {

  private val beCloseTo = haveRelativeErrorLessThan[Length](Percent(0.5))

  it should "correctly calculate the distance from NYC to LA" in {
    val newYork = City("New York", "NY", Coordinate(Degrees(40.6943), Degrees(-73.9249)))
    val losAngeles = City("Los Angeles", "CA", Coordinate(Degrees(34.1141), Degrees(-118.4068)))
    val expectedDistance = Kilometers(3953.62)
    val actualDistance = greatCircleDistance(newYork.coordinate, losAngeles.coordinate)
    actualDistance should beCloseTo(expectedDistance)
  }

}
