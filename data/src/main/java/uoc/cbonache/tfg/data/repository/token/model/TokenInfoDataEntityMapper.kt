package uoc.cbonache.tfg.data.repository.token.model

import uoc.cbonache.tfg.model.TokenInfo

/**
 * @author cbonache
 */
fun TokenInfoDataEntity.mapToTokenInfo(): TokenInfo {

    return TokenInfo(this.access_token,
            this.expires_in,
            this.refresh_token,
            this.tokenRetrievalDateInMillis)
}