package uoc.cbonache.tfg.data.repository.shippingRepository.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface GetShippingByIdQuery : Query {

    companion object Parameters {
        const val TOKEN = "TOKEN"
        const val ID = "ID"
    }
}