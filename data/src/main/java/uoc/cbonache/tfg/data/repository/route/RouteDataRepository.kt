package uoc.cbonache.tfg.data.repository.route

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.repository.Repository
import uoc.cbonache.tfg.data.repository.route.model.StepDataEntity
import uoc.cbonache.tfg.data.repository.route.model.mapToStep
import uoc.cbonache.tfg.data.repository.route.query.GetRouteQuery
import uoc.cbonache.tfg.model.Step
import uoc.cbonache.tfg.repository.RouteRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class RouteDataRepository @Inject constructor(routeApiDataSource: RouteApiDataSource) : Repository<Unit, StepDataEntity>(), RouteRepository {

    init {
        readableDataSources.add(routeApiDataSource)
    }

    override fun getRoute(originLat: Double, originLng: Double, destination: String): Result<List<Step>, *> {
        val params = HashMap<String, Any>()
        params.put(GetRouteQuery.originLat, originLat)
        params.put(GetRouteQuery.originLng, originLng)
        params.put(GetRouteQuery.destination, destination)
        val route = queryAll(GetRouteQuery::class.java, params)

        return route.map { it.map { it.mapToStep() } }
    }


}