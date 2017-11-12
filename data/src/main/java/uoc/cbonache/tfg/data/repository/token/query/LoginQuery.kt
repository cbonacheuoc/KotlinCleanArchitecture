package uoc.cbonache.tfg.data.repository.token.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface LoginQuery: Query {

    companion object Parameters {
        const val USERNAME: String = "USERNAME"
        const val PASSWORD: String = "PASSWORD"
    }
}