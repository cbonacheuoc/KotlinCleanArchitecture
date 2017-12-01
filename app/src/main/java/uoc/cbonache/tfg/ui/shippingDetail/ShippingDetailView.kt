package uoc.cbonache.tfg.ui.shippingDetail

import android.location.Location
import uoc.cbonache.tfg.ui.base.BaseView
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import com.google.android.gms.maps.model.PolylineOptions

/**
 * @author cbonache
 */
interface ShippingDetailView : BaseView {

    fun showShippingInfo(mapToShippingViewEntity: ShippingViewEntity)
    fun makeCall()
    fun requestCallPhonePermission()

    fun navigateToShippingSignActivity(idShipping: Long)
    fun navigateToShippingMapActivity(idShipping: Long)
}