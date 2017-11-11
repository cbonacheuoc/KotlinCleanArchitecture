package uoc.cbonache.tfg.data.repository.datasource

/**
 * Created by Borja on 30/3/17.
 */
interface Identifiable<out Key> {

    fun getKey(): Key
}
