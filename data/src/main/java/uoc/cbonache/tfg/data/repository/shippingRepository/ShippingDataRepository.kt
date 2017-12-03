package uoc.cbonache.tfg.data.repository.shippingRepository

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.repository.Repository
import uoc.cbonache.tfg.data.repository.shippingRepository.model.ShippingDataEntity
import uoc.cbonache.tfg.data.repository.shippingRepository.model.mapToShipping
import uoc.cbonache.tfg.data.repository.shippingRepository.query.GetShippingByCodeQuery
import uoc.cbonache.tfg.data.repository.shippingRepository.query.GetShippingByIdQuery
import uoc.cbonache.tfg.data.repository.shippingRepository.query.GetShippingsQuery
import uoc.cbonache.tfg.model.Shipping
import uoc.cbonache.tfg.repository.ShippingsRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingDataRepository @Inject constructor(shippingApiDataSource: ShippingApiDataSource) : Repository<String, ShippingDataEntity>(), ShippingsRepository {

    init {
        readableDataSources.add(shippingApiDataSource)
    }

    override fun getShippingsList(token: String): Result<List<Shipping>, *> {

        val params = HashMap<String, Any>()
        params.put(GetShippingsQuery.TOKEN, token)
        val shippingList = queryAll(GetShippingsQuery::class.java,params)

        return shippingList.map { it.map { it.mapToShipping() } }
    }

    override fun getShippingById(token: String, id: Long): Result<Shipping, *> {

        val params = HashMap<String, Any>()
        params.put(GetShippingByIdQuery.TOKEN, token)
        params.put(GetShippingByIdQuery.ID,id)
        val shippingList = query(GetShippingByIdQuery::class.java,params)

        return shippingList.map { it.mapToShipping() }
    }

    override fun getShippingByCode(token: String, code: String): Result<Shipping, *> {

        val params = HashMap<String, Any>()
        params.put(GetShippingByCodeQuery.TOKEN, token)
        params.put(GetShippingByCodeQuery.CODE,code)
        val packageList = query(GetShippingByCodeQuery::class.java,params)

        return packageList.map { it.mapToShipping() }
    }
}