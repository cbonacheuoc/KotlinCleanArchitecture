package uoc.cbonache.tfg.route

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.interactor.Interactor
import uoc.cbonache.tfg.model.Step
import uoc.cbonache.tfg.repository.RouteRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetRouteInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                             val repository: RouteRepository)
    : Interactor<List<Step>, GetRouteInteractor.Parameters>(postExecutionThread) {

    override fun run(params: Parameters): Result<List<Step>, *> {
        return repository.getRoute(params.latOrigin,params.lngOrigin,params.destination)
    }

    data class Parameters(val latOrigin: Double,val lngOrigin:Double, val destination: String)

}