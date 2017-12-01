package uoc.cbonache.tfg.ui.shippingMap

import android.location.Location
import uoc.cbonache.tfg.ui.base.BaseView
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import com.google.android.gms.maps.model.PolylineOptions

/**
 * @author cbonache
 */
interface ShippingMapView : BaseView {

    fun showShippingInfo(mapToShippingViewEntity: ShippingViewEntity)
    fun showLocation(currentLocation: Location?)
    fun requestLocationPermission()
    fun showMap()
    fun drawPolyline(polyOptions: PolylineOptions)
    fun getCurrentLocation() : Location?
    fun getDestination(): String
    fun navigateToGoogleMaps()
}