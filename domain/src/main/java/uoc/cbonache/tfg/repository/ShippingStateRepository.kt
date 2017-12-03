package uoc.cbonache.tfg.repository

import uoc.cbonache.tfg.Result

/**
 * @author cbonache
 */
interface ShippingStateRepository {

    fun setShippingState(id: Long, state: Int, token:String): Result<Boolean, *>

}