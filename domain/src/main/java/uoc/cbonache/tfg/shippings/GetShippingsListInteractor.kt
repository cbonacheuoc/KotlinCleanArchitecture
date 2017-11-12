package uoc.cbonache.tfg.shippings

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.flatMap
import uoc.cbonache.tfg.interactor.Interactor
import uoc.cbonache.tfg.model.Shipping
import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.repository.ShippingsRepository
import uoc.cbonache.tfg.token.GetTokenInteractor
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetShippingsListInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                                     val repository: ShippingsRepository,
                                                     val getTokenInteractor: GetTokenInteractor)

    : Interactor<List<Shipping>, Unit>(postExecutionThread) {

    override fun run(params: Unit): Result<List<Shipping>, *> {

        val result = this.getToken().map { value ->
            value.access_token }.flatMap { value ->
            repository.getShippingsList(value) }
        return result
    }

    private fun  getToken(): Result<TokenInfo, *> {

        return getTokenInteractor.run(Unit)
    }

}