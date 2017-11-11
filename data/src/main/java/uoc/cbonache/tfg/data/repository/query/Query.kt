package uoc.cbonache.tfg.data.repository.query

import uoc.cbonache.tfg.Result

/**
 * Created by Borja on 21/3/17.
 */
interface Query {

    fun queryAll(parameters: HashMap<String, *>? = null, queryable: Any? = null): Result<Collection<*>, *> {
        return Result.Failure()
    }
    fun query(parameters: HashMap<String, *>? = null, queryable: Any? = null): Result<*, *> {
        return Result.Failure()
    }

}