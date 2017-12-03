package uoc.cbonache.tfg.data.repository.shippingState

import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.ShippingStateQueries
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.query.Query
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingStateApiDataSource @Inject constructor(@ShippingStateQueries override val queries: MutableSet<Query>) : ReadableDataSource<Unit, Boolean>