package uoc.cbonache.tfg.data.repository.shippingState

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.repository.Repository
import uoc.cbonache.tfg.data.repository.shippingState.query.SetShippingStateQuery
import uoc.cbonache.tfg.repository.ShippingStateRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingStateDataRepository @Inject constructor(shippingStateApiDataSource: ShippingStateApiDataSource) : Repository<Unit, Boolean>(), ShippingStateRepository {

    init {
        readableDataSources.add(shippingStateApiDataSource)
    }

    override fun setShippingState(id: Long, state: Int, token: String): Result<Boolean, *> {
        val params = HashMap<String, Any>()
        params.put(SetShippingStateQuery.STATE, state)
        params.put(SetShippingStateQuery.ID,id)
        params.put(SetShippingStateQuery.TOKEN,token)
        val result = query(SetShippingStateQuery::class.java,params)

        return result

    }



}