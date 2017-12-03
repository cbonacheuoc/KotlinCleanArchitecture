package uoc.cbonache.tfg.repository

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.model.Shipping

/**
 * @author cbonache
 */
interface ShippingsRepository {

    fun getShippingsList(token: String): Result<List<Shipping>,*>
    fun getShippingById(token: String, id: Long): Result<Shipping, *>
    fun getShippingByCode(token: String, code: String): Result<Shipping,*>
}

