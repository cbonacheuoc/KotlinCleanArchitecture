package uoc.cbonache.tfg.data.network.service

import uoc.cbonache.tfg.data.network.ApiConstants
import uoc.cbonache.tfg.data.network.service.model.StatusInfo
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.*

/**
 * @author cbonache
 */
interface ShippingService {

    @GET(ApiConstants.GET_SHIPPINGS)
    fun getShippings(@Header("AUTHORIZATION") authorization: String): Call<JsonElement>

    @GET(ApiConstants.GET_SHIPPINGBYID)
    fun getShippingById(@Header("AUTHORIZATION") authorization: String, @Path("shippingid") id: String): Call<JsonElement>

    @GET(ApiConstants.GET_SHIPPINGBYCODE)
    fun getShippingByCode(@Header("AUTHORIZATION") authorization: String, @Path("code") code: String): Call<JsonElement>

    @POST(ApiConstants.SIGN)
    fun sign(@Header("AUTHORIZATION")authorization: String, @Body statusInfo: StatusInfo) : Call<JsonElement>
}