package uoc.cbonache.tfg.data.repository.route

import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.StepsListQueries
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.query.Query
import uoc.cbonache.tfg.data.repository.route.model.StepDataEntity
import javax.inject.Inject

/**
 * @author cbonache
 */
class RouteApiDataSource @Inject constructor(@StepsListQueries override val queries: MutableSet<Query>) : ReadableDataSource<Unit, StepDataEntity> {

}