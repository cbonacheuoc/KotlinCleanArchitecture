package uoc.cbonache.tfg.shippings

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.flatMap
import uoc.cbonache.tfg.interactor.Interactor
import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.repository.ShippingStateRepository
import uoc.cbonache.tfg.token.GetTokenInteractor
import javax.inject.Inject

/**
 * @author cbonache
 */
class SetShippingStateByIdInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                                        val repository: ShippingStateRepository,
                                                        val getTokenInteractor: GetTokenInteractor)

    : Interactor<Boolean, SetShippingStateByIdInteractor.Parameters>(postExecutionThread) {

    override fun run(params: Parameters): Result<Boolean, *> {

        val result = this.getToken().map { value ->
            value.access_token }.flatMap { value -> repository.setShippingState(params.id, params.state,value) }
        return result
    }

    data class Parameters(val id: Long, val state: Int)


    private fun  getToken(): Result<TokenInfo, *> {

        return getTokenInteractor.run(Unit)
    }

}