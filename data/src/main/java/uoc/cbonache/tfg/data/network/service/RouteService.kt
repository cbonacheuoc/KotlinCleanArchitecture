package uoc.cbonache.tfg.data.network.service

import uoc.cbonache.tfg.data.network.ApiConstants
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author cbonache
 */
interface RouteService {

    //?origin={originText}&destination={destinationText}&key={mapsKey}
    @GET(ApiConstants.MAPS_ROUTE)
    fun getRoute(@Query("origin") origin: String, @Query("destination") destination: String, @Query("key") key: String): Call<JsonElement>
}