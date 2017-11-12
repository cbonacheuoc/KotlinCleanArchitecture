package uoc.cbonache.tfg.data.network.service

import uoc.cbonache.tfg.data.network.ApiConstants
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author cbonache
 */
interface TokenService {

    @FormUrlEncoded
    @POST(ApiConstants.GET_TOKEN)
    fun getToken(@Field("grant_type") grand_Type: String,
                 @Field("client_id") client_id: String,
                 @Field("client_secret") client_secret: String,
                 @Field("username") username: String,
                 @Field("password") password: String): Call<JsonElement>

    @FormUrlEncoded
    @POST(ApiConstants.REFRESH_TOKEN)
    fun refreshToken(@Field("grant_type") grant_type: String,
                    @Field("client_id") client_id: String,
                    @Field("client_secret") client_secret: String,
                    @Field("refresh_token") refresh_token: String): Call<JsonElement>

}