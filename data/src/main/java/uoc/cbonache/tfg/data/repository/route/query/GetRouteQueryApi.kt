package uoc.cbonache.tfg.data.repository.route.query

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.MapsRetrofit
import uoc.cbonache.tfg.data.network.ConnectionChecker
import uoc.cbonache.tfg.data.network.parseResponse
import uoc.cbonache.tfg.data.network.service.RouteService
import uoc.cbonache.tfg.data.repository.route.model.StepDataEntity
import uoc.cbonache.tfg.model.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetRouteQueryApi @Inject constructor(@MapsRetrofit val retrofit: Retrofit,
                                           val connectionChecker: ConnectionChecker): GetRouteQuery {


    override fun queryAll(parameters: HashMap<String, *>?, queryable: Any?): Result<Collection<*>, *> {

        return if(connectionChecker.thereIsConnectivity()){

            val originLat = parameters?.get(GetRouteQuery.originLat) as Double
            val originLng = parameters[GetRouteQuery.originLng] as Double
            val destination = parameters[GetRouteQuery.destination] as String


            val service = retrofit.create(RouteService::class.java)
            val call = service.getRoute(originLat.toString()+","+originLng.toString(), destination, "AIzaSyCzWNU-DDJeSf4JQKs7nWPB56qnXcZfQ2Q")
            val response = call.execute()
            if(response.isSuccessful){

               // val stepsResponse = response.body()!!.getJsonObjectFromTag("routes").asJsonArray[0].getJsonObjectFromTag("legs").asJsonArray[0].getJsonObjectFromTag("steps")

                val result = response.parseResponse<List<Route>>("routes")

                val steps = result.map { it.first().legs.first().steps }

                return steps

            } else Result.Failure()


        } else {
            Result.Failure(NetworkException.NoInternetConnection())
        }
    }

    data class Route( val legs: List<Leg>)


    data class Leg(val steps: List<StepDataEntity>)
}