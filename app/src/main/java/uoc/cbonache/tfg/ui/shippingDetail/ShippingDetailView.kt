package uoc.cbonache.tfg.ui.shippingDetail

import uoc.cbonache.tfg.ui.base.BaseView
import uoc.cbonache.tfg.ui.model.ShippingViewEntity

/**
 * @author cbonache
 */
interface ShippingDetailView : BaseView {

    fun showShippingInfo(mapToShippingViewEntity: ShippingViewEntity)
    fun makeCall()
    fun requestCallPhonePermission()
    fun navigateToShippingSignActivity(code: String)
    fun navigateToShippingMapActivity(code: String)
    fun goToShippingList()
}