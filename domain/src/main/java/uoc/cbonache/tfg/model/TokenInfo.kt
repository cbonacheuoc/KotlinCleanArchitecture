package uoc.cbonache.tfg.model

/**
 * @author cbonache
 */
data class TokenInfo(val access_token: String,
                     val expires_in: Long,
                     val refresh_token: String,
                     val tokenRetrievalDateInMillis: Long){

    companion object {
        const private val TIME_CONVERTER: Int = 1000
    }

    fun isTokenStillValid(): Boolean {
        return tokenRetrievalDateInMillis / TIME_CONVERTER + expires_in > System.currentTimeMillis() / TIME_CONVERTER
    }
}
