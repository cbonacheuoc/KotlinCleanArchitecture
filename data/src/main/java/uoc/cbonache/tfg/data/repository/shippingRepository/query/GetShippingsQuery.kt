package uoc.cbonache.tfg.data.repository.shippingRepository.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface GetShippingsQuery : Query {

    companion object Parameters {
        const val TOKEN = "TOKEN"
    }
}