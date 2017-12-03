package uoc.cbonache.tfg.data.repository.shippingRepository.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface GetShippingByCodeQuery : Query {

    companion object Parameters {

        const val CODE = "CODE"
        const val TOKEN = "TOKEN"
    }
}