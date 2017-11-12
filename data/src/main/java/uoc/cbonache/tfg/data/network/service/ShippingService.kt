package uoc.cbonache.tfg.data.network.service

import uoc.cbonache.tfg.data.network.ApiConstants
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * @author cbonache
 */
interface ShippingService {

    @GET(ApiConstants.GET_SHIPPINGS)
    fun getShippings(@Header("AUTHORIZATION") authorization: String): Call<JsonElement>

    @GET(ApiConstants.GET_SHIPPINGBYID)
    fun getShippingById(@Header("AUTHORIZATION") authorization: String, @Path("shippingid") id: String): Call<JsonElement>
}