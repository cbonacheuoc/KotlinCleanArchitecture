package uoc.cbonache.tfg.model

/**
 * @author cbonache
 */
data class Credential(val grant_type: String,
                      val client_id: Int,
                      val client_secret: String,
                      val username: String,
                      val password: String) {
}