package uoc.cbonache.tfg.data.repository.shippingState.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface SetShippingStateQuery : Query {

    companion object Parameters {
        const val ID = "ID"
        const val STATE = "STATE"
        const val TOKEN = "TOKEN"

    }
}