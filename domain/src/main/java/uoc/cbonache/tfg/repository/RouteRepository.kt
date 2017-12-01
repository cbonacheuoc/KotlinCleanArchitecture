package uoc.cbonache.tfg.repository

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.model.Step

/**
 * @author cbonache
 */
interface RouteRepository {

    fun getRoute(originLat: Double, originLng:Double, destination: String): Result<List<Step>, *>
}