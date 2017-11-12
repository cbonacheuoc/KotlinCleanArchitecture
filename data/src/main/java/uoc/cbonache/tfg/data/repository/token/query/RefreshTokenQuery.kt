package uoc.cbonache.tfg.data.repository.token.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface RefreshTokenQuery: Query {

    companion object Parameters {
        const val GRANT_TYPE = "GRANT_TYPE"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}