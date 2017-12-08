package uoc.cbonache.tfg.ui.shippingSign

import uoc.cbonache.tfg.ui.base.BaseView
import uoc.cbonache.tfg.ui.model.ShippingViewEntity

/**
 * @author cbonache
 */
interface ShippingSignView : BaseView {

    fun signShipping(mapToShippingViewEntity: ShippingViewEntity)
    fun goToShippingList()

}