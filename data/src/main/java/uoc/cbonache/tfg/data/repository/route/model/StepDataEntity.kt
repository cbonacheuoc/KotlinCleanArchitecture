package uoc.cbonache.tfg.data.repository.route.model

/**
 * @author cbonache
 */
data class StepDataEntity(val end_location: LocationDataEntity,
                          val polyline: PolylineDataEntity,
                          val start_location: LocationDataEntity)


data class PolylineDataEntity(val points: String)

data class LocationDataEntity(val lat: Double, val lng: Double)