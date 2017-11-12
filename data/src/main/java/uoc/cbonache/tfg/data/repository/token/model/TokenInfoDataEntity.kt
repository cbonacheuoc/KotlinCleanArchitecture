package uoc.cbonache.tfg.data.repository.token.model

/**
 * @author cbonache
 */
data class TokenInfoDataEntity(var access_token: String,
                               var expires_in: Long,
                               var refresh_token: String,
                               var tokenRetrievalDateInMillis: Long = 0L){

    companion object {
        const private val TIME_CONVERTER: Int = 1000
    }

    fun setCurrentTimeAsRetrievalDate() {
        tokenRetrievalDateInMillis = System.currentTimeMillis()
    }

    fun isTokenStillValid(): Boolean {
        return tokenRetrievalDateInMillis / TIME_CONVERTER + expires_in > System.currentTimeMillis() / TIME_CONVERTER
    }
}
