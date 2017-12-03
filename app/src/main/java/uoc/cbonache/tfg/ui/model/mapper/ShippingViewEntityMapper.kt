package uoc.cbonache.tfg.ui.model.mapper

import uoc.cbonache.tfg.model.Shipping
import uoc.cbonache.tfg.ui.model.ShippingViewEntity

/**
 * @author cbonache
 */
fun Shipping.mapToShippingViewEntity(): ShippingViewEntity{

    return ShippingViewEntity(this.id,
            this.code,
            this.states,
            this.contact_person,
            this.email,
            this.number,
            this.weight,
            this.size,
            this.fragile,
            this.telephone,
            this.address,
            this.cp,
            this.city,
            this.state,
            this.country,
            this.created_at,
            this.updated_at)
}