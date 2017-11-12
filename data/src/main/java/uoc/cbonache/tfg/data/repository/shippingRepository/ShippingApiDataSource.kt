package uoc.cbonache.tfg.data.repository.shippingRepository

import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.ShippingsListQueries
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.shippingRepository.model.ShippingDataEntity
import uoc.cbonache.tfg.data.repository.query.Query
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingApiDataSource @Inject constructor(@ShippingsListQueries override val queries: MutableSet<Query>) : ReadableDataSource<String, ShippingDataEntity>
