package uoc.cbonache.tfg.data.repository.shippingRepository.query

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.network.ConnectionChecker
import uoc.cbonache.tfg.data.network.parseResponse
import uoc.cbonache.tfg.data.network.service.ShippingService
import uoc.cbonache.tfg.data.repository.shippingRepository.model.ShippingDataEntity
import uoc.cbonache.tfg.model.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetShippingsQueryApi @Inject constructor(val retrofit: Retrofit,
                                              val connectionChecker: ConnectionChecker): GetShippingsQuery {

    private val BEARER = "Bearer "

    override fun queryAll(parameters: HashMap<String, *>?, queryable: Any?): Result<Collection<*>, *> {

        return if(connectionChecker.thereIsConnectivity()){

            val token = parameters?.get(GetShippingsQuery.Parameters.TOKEN) as String
            val authorization = BEARER + token
            val service = retrofit.create(ShippingService::class.java)
            val call = service.getShippings(authorization)
            val response = call.execute()
            if(response.isSuccessful){

                val resultList = response.parseResponse<List<ShippingDataEntity>>()

                return resultList
            } else Result.Failure()

        } else {
            Result.Failure(NetworkException.NoInternetConnection())
        }
    }
}