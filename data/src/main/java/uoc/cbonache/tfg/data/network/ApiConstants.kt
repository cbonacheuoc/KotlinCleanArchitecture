package uoc.cbonache.tfg.data.network

/**
 * @author cbonache
 */
interface ApiConstants {

    companion object URL {
        const val BASEURL = "http://transpdroid.tresipunt.com/"
        const val GET_TOKEN = "/oauth/token"
        const val REFRESH_TOKEN = "/oauth/token"
        const val GET_SHIPPINGS = "/api/shippings/user"
        const val GET_SHIPPINGBYID = "/api/shipping/id/{shippingid}/data"
        const val GET_SHIPPINGBYCODE = "/api/shipping/code/{shippingcode}/data"
        const val GET_SHIPPINGDESTINY = "/api/shipping/id/{shippingid}/destiny"
        const val BASE_MAPS = "https://maps.googleapis.com/"
        const val MAPS_ROUTE = "/maps/api/directions/json"
    }
}