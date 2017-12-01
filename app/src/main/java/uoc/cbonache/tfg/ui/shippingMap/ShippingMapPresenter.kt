package uoc.cbonache.tfg.ui.shippingMap

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
class ShippingMapPresenter @Inject constructor(val view: ShippingMapView,
                                                 val getShippingByIdInteractor: GetShippingByIdInteractor,
                                                 val getRoute: GetRouteInteractor,
                                                 val exceptionHandler: AndroidExceptionHandler) {

    fun onStart(shippingID: Long) {

        getShippingByIdInteractor.execute(GetShippingByIdInteractor.Parameters(shippingID)) { result ->
            result.success { shippingMap ->

                view.showShippingInfo(shippingMap.mapToShippingViewEntity())
                view.showMap()
            }

            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

    fun onLocationPermissionGranted() {

        //TODO: get origin and destination

        val currentLocation = view.getCurrentLocation()
        view.showLocation(currentLocation)

        currentLocation?.let {
            val latlngLocation = LatLng(currentLocation.latitude, currentLocation.longitude)
            val destination = view.getDestination()

            getRoute.execute(GetRouteInteractor.Parameters(latlngLocation.latitude,latlngLocation.longitude, destination)) { result ->
                result.success { steps ->

                    getPolylineFromSteps(steps)

                }

                result.failure { exception ->
                    exceptionHandler.notifyException(view, exception)
                }
            }
        }
    }

    private fun checkIfPermissionsNeeded(context: Context, permissions: ArrayList<String>): Boolean {


        for (permission in permissions) {
            var permissionCallCheck = ContextCompat.checkSelfPermission(context,
                    permission)

            if (PackageManager.PERMISSION_GRANTED != permissionCallCheck) return true
        }

        return false
    }

    fun onMapReady(context: Context) {

        //TODO get location
        getCurrentPosition(context)
        //Draw route
        //https://maps.googleapis.com/maps/api/directions/json?origin=&destination=&key=

    }

    private fun getCurrentPosition(context: Context) {

        if (checkIfPermissionsNeeded(context, arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))) {
            requestLocationPermission()
        } else {
            onLocationPermissionGranted()
        }
    }

    private fun requestLocationPermission() {

        view.requestLocationPermission()
    }


    private fun getPolylineFromSteps(steps: List<Step>) {

        val polyOptions = PolylineOptions()
        polyOptions.color(Color.BLUE)

        val listSetps = arrayListOf<LatLng>()

        for (step in steps) {

            listSetps.add(LatLng(step.end_location.lat, step.end_location.lng))

        }

        polyOptions.addAll(listSetps)

        view.drawPolyline(polyOptions)

    }

    fun onClickMap() {

        view.navigateToGoogleMaps()
    }
}