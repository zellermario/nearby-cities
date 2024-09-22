package domain

object UsaStates {

  val stateCodes: Set[String] = Set(
    "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",
    "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR",
    "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY")

  val adjacentStates: Map[String, Seq[String]] = Map(
    "AK" -> Seq("AK"),
    "AL" -> Seq("AL", "MS", "TN", "GA", "FL"),
    "AR" -> Seq("AR", "MO", "TN", "MS", "LA", "TX", "OK"),
    "AZ" -> Seq("AZ", "CA", "NV", "UT", "CO", "NM"),
    "CA" -> Seq("CA", "OR", "NV", "AZ"),
    "CO" -> Seq("CO", "WY", "NE", "KS", "OK", "NM", "AZ", "UT"),
    "CT" -> Seq("CT", "NY", "MA", "RI"),
    "DC" -> Seq("DC", "MD", "VA"),
    "DE" -> Seq("DE", "MD", "PA", "NJ"),
    "FL" -> Seq("FL", "AL", "GA"),
    "GA" -> Seq("GA", "FL", "AL", "TN", "NC", "SC"),
    "HI" -> Seq("HI"),
    "IA" -> Seq("IA", "MN", "WI", "IL", "MO", "NE", "SD"),
    "ID" -> Seq("ID", "MT", "WY", "UT", "NV", "OR", "WA"),
    "IL" -> Seq("IL", "IN", "KY", "MO", "IA", "WI"),
    "IN" -> Seq("IN", "MI", "OH", "KY", "IL"),
    "KS" -> Seq("KS", "NE", "MO", "OK", "CO"),
    "KY" -> Seq("KY", "IN", "OH", "WV", "VA", "TN", "MO", "IL"),
    "LA" -> Seq("LA", "TX", "AR", "MS"),
    "MA" -> Seq("MA", "RI", "CT", "NY", "NH", "VT"),
    "MD" -> Seq("MD", "VA", "WV", "PA", "DC", "DE"),
    "ME" -> Seq("ME", "NH"),
    "MI" -> Seq("MI", "WI", "IN", "OH"),
    "MN" -> Seq("MN", "WI", "IA", "SD", "ND"),
    "MO" -> Seq("MO", "IA", "IL", "KY", "TN", "AR", "OK", "KS", "NE"),
    "MS" -> Seq("MS", "LA", "AR", "TN", "AL"),
    "MT" -> Seq("MT", "ND", "SD", "WY", "ID"),
    "NC" -> Seq("NC", "VA", "TN", "GA", "SC"),
    "ND" -> Seq("ND", "MN", "SD", "MT"),
    "NE" -> Seq("NE", "SD", "IA", "MO", "KS", "CO", "WY"),
    "NH" -> Seq("NH", "VT", "ME", "MA"),
    "NJ" -> Seq("NJ", "DE", "PA", "NY"),
    "NM" -> Seq("NM", "AZ", "UT", "CO", "OK", "TX"),
    "NV" -> Seq("NV", "ID", "UT", "AZ", "CA", "OR"),
    "NY" -> Seq("NY", "NJ", "PA", "VT", "MA", "CT"),
    "OH" -> Seq("OH", "PA", "WV", "KY", "IN", "MI"),
    "OK" -> Seq("OK", "KS", "MO", "AR", "TX", "NM", "CO"),
    "OR" -> Seq("OR", "CA", "NV", "ID", "WA"),
    "PA" -> Seq("PA", "NY", "NJ", "DE", "MD", "WV", "OH"),
    "RI" -> Seq("RI", "CT", "MA"),
    "SC" -> Seq("SC", "GA", "NC"),
    "SD" -> Seq("SD", "ND", "MN", "IA", "NE", "WY", "MT"),
    "TN" -> Seq("TN", "KY", "VA", "NC", "GA", "AL", "MS", "AR", "MO"),
    "TX" -> Seq("TX", "NM", "OK", "AR", "LA"),
    "UT" -> Seq("UT", "ID", "WY", "CO", "NM", "AZ", "NV"),
    "VA" -> Seq("VA", "NC", "TN", "KY", "WV", "MD", "DC"),
    "VT" -> Seq("VT", "NY", "NH", "MA"),
    "WA" -> Seq("WA", "ID", "OR"),
    "WI" -> Seq("WI", "MI", "MN", "IA", "IL"),
    "WV" -> Seq("WV", "OH", "PA", "MD", "VA", "KY"),
    "WY" -> Seq("WY", "MT", "SD", "NE", "CO", "UT", "ID"))

}
