package uoc.cbonache.tfg.data.repository.route.model

import uoc.cbonache.tfg.model.Location
import uoc.cbonache.tfg.model.Polyline
import uoc.cbonache.tfg.model.Step

/**
 * @author cbonache
 */
fun StepDataEntity.mapToStep(): Step {

    return Step(this.end_location.mapToLocation(),this.polyline.mapToPolyline(),this.start_location.mapToLocation())
}

fun LocationDataEntity.mapToLocation(): Location{

    return Location(this.lat,this.lng)
}

fun PolylineDataEntity.mapToPolyline(): Polyline {

    return Polyline(this.points)
}