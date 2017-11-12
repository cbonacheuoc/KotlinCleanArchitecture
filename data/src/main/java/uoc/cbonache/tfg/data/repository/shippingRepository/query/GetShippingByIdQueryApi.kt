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
class GetShippingByIdQueryApi @Inject constructor(val retrofit: Retrofit,
                                                 val connectionChecker: ConnectionChecker): GetShippingByIdQuery {

    private val BEARER = "Bearer "


    override fun query(parameters: HashMap<String, *>?, queryable: Any?): Result<*, *> {

        return if(connectionChecker.thereIsConnectivity()){

            val token = parameters?.get(GetShippingByIdQuery.TOKEN) as String
            val packageId = parameters[GetShippingByIdQuery.ID] as Long

            val authorization = BEARER + token


            val service = retrofit.create(ShippingService::class.java)
            val call = service.getShippingById(authorization,packageId.toString())
            val response = call.execute()
            if(response.isSuccessful){

                val result = response.parseResponse<ShippingDataEntity>()

                return result

            } else Result.Failure()


        } else {
            Result.Failure(NetworkException.NoInternetConnection())
        }
    }
}