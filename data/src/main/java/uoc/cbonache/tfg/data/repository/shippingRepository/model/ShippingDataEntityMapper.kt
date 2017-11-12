package uoc.cbonache.tfg.data.repository.shippingRepository.model

import uoc.cbonache.tfg.model.Shipping

/**
 * @author cbonache
 */
fun ShippingDataEntity.mapToShipping(): Shipping {

    return Shipping(this.id,
            this.code,
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