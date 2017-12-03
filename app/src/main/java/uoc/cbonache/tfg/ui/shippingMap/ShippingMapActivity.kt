package uoc.cbonache.tfg.ui.shippingMap

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.ViewGroup
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.ManagePermissions
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.jetbrains.anko.contentView
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingMapActivity: BaseActivity(), ShippingMapView, OnMapReadyCallback {


    companion object {

        const val CODE = "code"
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, ShippingMapActivity::class.java)
        }
    }

    @Inject lateinit var presenter: ShippingMapPresenter
    @Inject lateinit var managePermissions: ManagePermissions
    @Inject lateinit var navigator: Navigator
    lateinit var locationManager: LocationManager
    lateinit var mapFragment: MapFragment
    lateinit var googleMap: GoogleMap
    lateinit var geocoder: Geocoder
    lateinit var shippingDestination: String

    override fun onRequestLayout(): Int {
        return R.layout.activity_shipping_map
    }

    override fun onViewLoaded() {
        val code = intent.extras.get(CODE) as String
        setUpActionBar()
        presenter.onStart(code)
    }

    override fun showMap() {

        if (googleServicesAvailable()) {
            mapFragment = fragmentManager.findFragmentById(R.id.frame_map) as MapFragment
            mapFragment.getMapAsync(this)
        }
    }

    override fun drawPolyline(polyOptions: PolylineOptions) {

        googleMap.addPolyline(polyOptions)

        setUpMapClick()
    }

    private fun setUpMapClick() {

        googleMap.setOnMapLongClickListener {
            presenter.onClickMap()
        }
    }

    @SuppressLint("MissingPermission")
    override fun getCurrentLocation(): Location? {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    }

    override fun getDestination(): String {
        return shippingDestination
    }

    override fun navigateToGoogleMaps() {

        navigator.navigateToGoogleMapsApps(this, shippingDestination)
    }

    private fun googleServicesAvailable(): Boolean {

        val api = GoogleApiAvailability.getInstance()
        val isAvailable = api.isGooglePlayServicesAvailable(this)
        return isAvailable == ConnectionResult.SUCCESS
    }


    private fun setUpActionBar() {

        supportActionBar?.apply {
            title = getString(R.string.shipping_map)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun showShippingInfo(shippingViewEntity: ShippingViewEntity) {
        shippingDestination = shippingViewEntity.address + " " + shippingViewEntity.cp + " " + shippingViewEntity.city + " " + shippingViewEntity.country
    }

    override fun requestLocationPermission() {

        val permissionsMessage = getString(R.string.permissions_location_message)
        val listener =
                managePermissions.setAllPermissionsListener(this,
                        contentView as ViewGroup, permissionsMessage, { presenter.onLocationPermissionGranted() })
        managePermissions.setRequestPermissions(this, arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), listener)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        this.googleMap = googleMap
        geocoder = Geocoder(this)
        goToLocation(15f)
        presenter.onMapReady(this)

    }


    private fun goToLocation(zoom: Float) {

        val address = geocoder.getFromLocationName(shippingDestination, 1)
        val it = address.first()
        val ll = LatLng(it.latitude, it.longitude)
        val updateCamera = CameraUpdateFactory.newLatLngZoom(ll, zoom)

        googleMap.moveCamera(updateCamera)
        googleMap.addMarker(MarkerOptions().position(ll))
    }

    override fun showLocation(currentLocation: Location?) {

        currentLocation?.let {
            val latlng = LatLng(currentLocation.latitude, currentLocation.longitude)

            val bitmap = getBitmapFromVectorDrawable(this, R.drawable.ic_noun_814594_cc)
            googleMap.addMarker(MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromBitmap(bitmap)))
        }
    }

    private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
        var drawable = ContextCompat.getDrawable(context, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        return bitmap
    }
}
