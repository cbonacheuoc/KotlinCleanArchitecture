package uoc.cbonache.tfg.ui.shippingDetail

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.support.v4.content.ContextCompat
import uoc.cbonache.tfg.model.Step
import uoc.cbonache.tfg.shippings.GetShippingByIdInteractor
import uoc.cbonache.tfg.route.GetRouteInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import uoc.cbonache.tfg.ui.model.mapper.mapToShippingViewEntity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import javax.inject.Inject


/**
 * @author cbonache
 */
class ShippingDetailPresenter @Inject constructor(val view: ShippingDetailView,
                                                 val getShippingByIdInteractor: GetShippingByIdInteractor,
                                                 val exceptionHandler: AndroidExceptionHandler) {

    fun onStart(shippingID: Long) {

        getShippingByIdInteractor.execute(GetShippingByIdInteractor.Parameters(shippingID)) { result ->
            result.success { shippingDetail ->

                view.showShippingInfo(shippingDetail.mapToShippingViewEntity())
            }

            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

    fun onCallButtonPressed(context: Context) {

        if (checkIfPermissionsNeeded(context, arrayListOf(Manifest.permission.CALL_PHONE))) {
            requestCallPermission()
        } else {
            onCallPermissionGranted()
        }
    }

    fun onCallPermissionGranted() {
        view.makeCall()
    }

    private fun requestCallPermission() {
        view.requestCallPhonePermission()
    }

    private fun checkIfPermissionsNeeded(context: Context, permissions: ArrayList<String>): Boolean {


        for (permission in permissions) {
            var permissionCallCheck = ContextCompat.checkSelfPermission(context,
                    permission)

            if (PackageManager.PERMISSION_GRANTED != permissionCallCheck) return true
        }

        return false
    }

    fun onShippingSignButtonPressed(idShipping: Long) {
        view.navigateToShippingSignActivity(idShipping)
    }

    fun onShippingMapButtonPressed(idShipping: Long) {
        view.navigateToShippingMapActivity(idShipping)
    }
}