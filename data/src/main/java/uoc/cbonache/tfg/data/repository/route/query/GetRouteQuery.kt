package uoc.cbonache.tfg.data.repository.route.query

import uoc.cbonache.tfg.data.repository.query.Query

/**
 * @author cbonache
 */
interface GetRouteQuery: Query {

    companion object Parameters {
        const val originLat = "ORIGINLAT"
        const val originLng = "ORIGINLNG"
        const val destination = "DESTINATION"
    }
}