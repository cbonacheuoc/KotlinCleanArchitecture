package uoc.cbonache.tfg.data.repository.shippingState.query

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.network.ConnectionChecker
import uoc.cbonache.tfg.data.network.service.ShippingService
import uoc.cbonache.tfg.data.network.service.model.StatusInfo
import uoc.cbonache.tfg.model.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author cbonache
 */
class SetShippingStateQueryApi @Inject constructor(val retrofit: Retrofit,
                                                val connectionChecker: ConnectionChecker) : SetShippingStateQuery {

    private val BEARER = "Bearer "


    override fun query(parameters: HashMap<String, *>?, queryable: Any?): Result<*, *> {

        return if (connectionChecker.thereIsConnectivity()) {

            val id = parameters?.get(SetShippingStateQuery.ID) as Long
            val state = parameters[SetShippingStateQuery.STATE] as Int
            val token = parameters[SetShippingStateQuery.TOKEN] as String

            val authorization = BEARER + token


            val service = retrofit.create(ShippingService::class.java)

            val call = service.sign(authorization, StatusInfo(id,state) )

            val response = call.execute()
            if (response.isSuccessful) {

                return Result.of { true }

            } else Result.Failure()


        } else {
            Result.Failure(NetworkException.NoInternetConnection())
        }
    }
}