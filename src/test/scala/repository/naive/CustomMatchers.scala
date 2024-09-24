package repository.naive

import org.scalatest.matchers.{MatchResult, Matcher}
import squants.{Dimensionless, Percent, Quantity}

import scala.math.abs

trait CustomMatchers {

  class RelativeErrorMatcher[T <: Quantity[T]](maxRelativeError: Dimensionless, expected: T) extends Matcher[T] {

    private def relativeError(actual: T, expected: T) = Percent(abs((actual - expected) / expected) * 100)

    def apply(actual: T): MatchResult = {
      MatchResult(
        relativeError(actual, expected) <= maxRelativeError,
        s"Relative error of $actual compared to $expected was not within $maxRelativeError%.",
        s"Relative error of $actual compared to $expected was within $maxRelativeError%.",
      )
    }
    
  }

  def haveRelativeErrorLessThan[T <: Quantity[T]](maxRelativeError: Dimensionless)(expected: T) 
    = new RelativeErrorMatcher(maxRelativeError, expected)

}
