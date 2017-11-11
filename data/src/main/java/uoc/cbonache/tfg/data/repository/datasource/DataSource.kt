package uoc.cbonache.tfg.data.repository.datasource

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.repository.implements
import uoc.cbonache.tfg.data.repository.query.Query

/**
 * Created by Borja on 6/3/17.
 */
interface CacheDataSource<Key, Value> : ReadableDataSource<Key, Value>, WritableDataSource<Key, Value> {

    fun isValid(value: Value): Boolean
}

interface WritableDataSource<Key, Value> {

    fun deleteByKey(key: Key): Result<Unit, *> = Result.Failure()

    fun deleteAll(): Result<Unit, *> = Result.Failure()

    fun addOrUpdate(value: Value): Result<Value, *> = Result.Failure()

    fun addOrUpdateAll(values: Collection<Value>): Result<Collection<Value>, *> = Result.Failure()
}

interface ReadableDataSource<Key, out Value> {

    val queries: MutableSet<Query>

    fun getByKey(key: Key): Result<Value, *> = Result.Failure()

    fun getAll(): Result<Collection<Value>, *> = Result.Failure()

    fun queryAll(query: Class<*>, parameters: HashMap<String, *>? = null): Result<Collection<Value>, *> {
        queries.forEach { possibleQuery ->
            if (possibleQuery.implements(query)) {
                return possibleQuery.queryAll(parameters) as Result<Collection<Value>, *>
            }
        }
        return Result.Failure()
    }

    fun query(query: Class<*>, parameters: HashMap<String, *>? = null): Result<Value, *> {
        queries.forEach { possibleQuery ->
            if (possibleQuery.implements(query)) {
                return possibleQuery.query(parameters) as Result<Value, *>
            }
        }
        return Result.Failure()
    }

}
