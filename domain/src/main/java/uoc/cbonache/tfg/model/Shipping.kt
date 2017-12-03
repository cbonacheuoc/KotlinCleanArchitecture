package uoc.cbonache.tfg.model

/**
 * @author cbonache
 */
data class Shipping(val id: Long,
                   val code: String,
                   val states: Int,
                   val contact_person: String,
                   val email: String,
                   val number: String,
                   val weight: String,
                   val size: String,
                   val fragile: String,
                   val telephone: String,
                   val address: String,
                   val cp: String,
                   val city: String,
                   val state: String,
                   val country: String,
                   val created_at: String,
                   val updated_at: String)