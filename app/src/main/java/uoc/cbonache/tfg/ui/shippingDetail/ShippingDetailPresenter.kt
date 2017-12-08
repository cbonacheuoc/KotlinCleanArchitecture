package uoc.cbonache.tfg.ui.shippingDetail

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import uoc.cbonache.tfg.shippings.GetShippingByCodeInteractor
import uoc.cbonache.tfg.shippings.SetShippingStateByIdInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import uoc.cbonache.tfg.ui.model.mapper.mapToShippingViewEntity
import uoc.cbonache.tfg.ui.ShippingStates
import javax.inject.Inject


/**
 * @author cbonache
 */
class ShippingDetailPresenter @Inject constructor(val view: ShippingDetailView,
                                                  val getShippingByCodeInteractor: GetShippingByCodeInteractor,
                                                  val setShippingStateByIdInteractor: SetShippingStateByIdInteractor,
                                                  val exceptionHandler: AndroidExceptionHandler) {

    fun onStart(code: String) {

        getShippingByCodeInteractor.execute(GetShippingByCodeInteractor.Parameters(code)) { result ->
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

    fun onShippingSignButtonPressed(code: String) {
        view.navigateToShippingSignActivity(code)
    }

    fun onShippingMapButtonPressed(code: String) {
        view.navigateToShippingMapActivity(code)
    }

    fun onButton404ButtonPressed(shippingID: Long) {

        setShippingState(shippingID,ShippingStates.USER_NOT_FOUND.state)
    }

    fun onButton502ButtonPressed(shippingID: Long) {

        setShippingState(shippingID,ShippingStates.WRONG_ADDRESS.state)
    }

    private fun setShippingState(shippingID: Long, state: Int){

        setShippingStateByIdInteractor.execute(SetShippingStateByIdInteractor.Parameters(shippingID,state)) { result ->
            result.success { result ->
                view.goToShippingList()
            }
            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }
}