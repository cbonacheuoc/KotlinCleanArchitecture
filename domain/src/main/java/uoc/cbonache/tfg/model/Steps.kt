package uoc.cbonache.tfg.model

/**
 * @author cbonache
 */
data class Step(val end_location: Location,
                 val polyline: Polyline,
                 val start_location: Location)

data class Polyline(val points: String)

data class Location(val lat: Double, val lng: Double)