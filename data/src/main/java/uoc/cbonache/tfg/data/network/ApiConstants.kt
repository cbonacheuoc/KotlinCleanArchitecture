package uoc.cbonache.tfg.data.network

/**
 * @author cbonache
 */
interface ApiConstants {

    companion object URL {
        const val BASEURL = "http://transpdroid.tresipunt.com/"
        const val GET_TOKEN = "/oauth/token"
        const val REFRESH_TOKEN = "/oauth/token"
        const val GET_PACKAGES = "/api/shippings/user"
        const val GET_PACKAGEBYID = "/api/shipping/id/{packageid}/data"
    }
}