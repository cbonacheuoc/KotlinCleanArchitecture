package uoc.cbonache.tfg.data.repository.policy

/**
 * Created by Borja on 5/4/17.
 */
enum class WritePolicy {
    WRITE_CACHE_ONLY,
    WRITE_ALL;

    fun writeCache(): Boolean = this == WRITE_CACHE_ONLY

}
