package repository.indexed

import com.google.common.geometry.{S2ClosestPointQuery, S2LatLng, S2Point, S2PointIndex}
import domain.City
import repository.CityRepository
import scala.jdk.StreamConverters._

class IndexedCityRepository extends CityRepository {

  private var cities: Seq[City] = Seq.empty
  private val index: S2PointIndex[City] = new S2PointIndex[City]()
  
  private def toS2Point(city: City): S2Point = 
    S2LatLng.fromDegrees(
      city.coordinate.latitude.toDegrees,
      city.coordinate.longitude.toDegrees).toPoint

  override def loadCities(cities: Seq[City]): Unit = {
    cities.foreach(city =>
      val s2Point = toS2Point(city)
      index.add(s2Point, city)
    )
    this.cities = cities
  }

  override def getAllCities: Seq[City] = cities

  override def findCity(name: String, stateCode: String): Option[City] =
    cities.find(c => c.name == name && c.stateCode == stateCode)

  override def findNearestCities(sourceCity: City, numberOfCities: Int): Seq[City] = {
    val query = S2ClosestPointQuery[City](index)
    query.setMaxPoints(numberOfCities)
    val sourceS2Point = toS2Point(sourceCity)
    val results = query.findClosestPoints(sourceS2Point)
    results.stream().map(result => result.entry().data()).toScala(Seq)
  }

}
