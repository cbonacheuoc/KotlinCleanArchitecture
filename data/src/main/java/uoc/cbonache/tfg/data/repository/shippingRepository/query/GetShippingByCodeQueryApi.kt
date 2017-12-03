package uoc.cbonache.tfg.data.repository.shippingRepository.query

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.network.ConnectionChecker
import uoc.cbonache.tfg.data.network.parseResponse
import uoc.cbonache.tfg.data.network.service.ShippingService
import uoc.cbonache.tfg.data.repository.shippingRepository.model.ShippingDataEntity
import uoc.cbonache.tfg.model.exceptions.NetworkException
import uoc.cbonache.tfg.model.exceptions.ShippingException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetShippingByCodeQueryApi @Inject constructor(val retrofit: Retrofit,
                                                    val connectionChecker: ConnectionChecker): GetShippingByCodeQuery {

    private val BEARER = "Bearer "


    override fun query(parameters: HashMap<String, *>?, queryable: Any?): Result<*, *> {

        return if(connectionChecker.thereIsConnectivity()){

            val token = parameters?.get(GetShippingByCodeQuery.TOKEN) as String
            val code = parameters[GetShippingByCodeQuery.CODE] as String

            val authorization = BEARER + token


            val service = retrofit.create(ShippingService::class.java)
            val call = service.getShippingByCode(authorization,code)
            val response = call.execute()
            if(response.isSuccessful){

                val result = response.parseResponse<ShippingDataEntity>()

                return result

            } else Result.Failure(ShippingException.NoShippingException())


        } else {
            Result.Failure(NetworkException.NoInternetConnection())
        }
    }
}