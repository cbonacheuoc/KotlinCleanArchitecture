package uoc.cbonache.tfg.ui.shippingDetail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.ManagePermissions
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import uoc.cbonache.tfg.ui.setPrefixTextBold
import kotlinx.android.synthetic.main.activity_shipping_detail.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.contentView
import org.jetbrains.anko.yesButton
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingDetailActivity: BaseActivity(), ShippingDetailView {


    companion object {

        const val CODE = "code"
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, ShippingDetailActivity::class.java)
        }
    }

    @Inject lateinit var presenter: ShippingDetailPresenter
    @Inject lateinit var managePermissions: ManagePermissions
    @Inject lateinit var navigator: Navigator
    lateinit var shippingDestination: String
    lateinit var phoneNumber: String

    override fun onRequestLayout(): Int {
        return R.layout.activity_shipping_detail
    }

    override fun onViewLoaded() {

        val code = intent.extras.get(CODE) as String

        setUpActionBar()

        sign.setOnClickListener {
            presenter.onShippingSignButtonPressed(code)
        }

        map.setOnClickListener {
            presenter.onShippingMapButtonPressed(code)
        }

        presenter.onStart(code)
    }

    private fun setUpActionBar() {

        supportActionBar?.apply {
            title = getString(R.string.shipping_detail)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun showShippingInfo(shippingViewEntity: ShippingViewEntity) {

        setSignListeners(shippingViewEntity.id)

        shippingDestination = shippingViewEntity.address + " " + shippingViewEntity.cp + " " + shippingViewEntity.city + " " + shippingViewEntity.country
        contact.setPrefixTextBold("Contacto:", shippingViewEntity.contact_person)
        address.setPrefixTextBold(getString(R.string.address), shippingViewEntity.address, " ")
        number.setPrefixTextBold(getString(R.string.number), shippingViewEntity.number)
        weight.setPrefixTextBold("Peso:",shippingViewEntity.weight)
        size.setPrefixTextBold("Altura:",shippingViewEntity.size)
        changeFragileIconVisibility(shippingViewEntity.fragile)
        changePhoneIconVisibility(shippingViewEntity.telephone)

        //TODO: Parsear estado

    }

    private fun setSignListeners(shippingID: Long) {

        button404.setOnClickListener {
            presenter.onButton404ButtonPressed(shippingID)
        }

        button502.setOnClickListener {
            presenter.onButton502ButtonPressed(shippingID)
        }
    }

    private fun changePhoneIconVisibility(phone: String) {

        if (phone.isNotEmpty()) {
            phoneButton.visibility = View.VISIBLE
            phoneNumber = phone
            phoneButton.setOnClickListener {
                presenter.onCallButtonPressed(this)
            }

        } else phoneButton.visibility = View.GONE
    }

    private fun changeFragileIconVisibility(isFragile: String) {
        if (isFragile == "1") fragile_icon.visibility = View.VISIBLE
        else fragile_icon.visibility = View.GONE
    }

    override fun requestCallPhonePermission() {

        val permissionsMessage = getString(R.string.permissions_call_message)
        val listener =
                managePermissions.setAllPermissionsListener(this,
                        contentView as ViewGroup, permissionsMessage, { presenter.onCallPermissionGranted() })
        managePermissions.setRequestPermissions(this, arrayListOf(Manifest.permission.CALL_PHONE), listener)
    }

    @SuppressLint("MissingPermission")
    override fun makeCall() {
        val userTelephone = "tel:"+phoneNumber
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse(userTelephone)
        startActivity(callIntent)
    }

    override fun navigateToShippingSignActivity(code: String) {
        navigator.navigateToShippingSign(this, code)
    }

    override fun navigateToShippingMapActivity(code: String) {
        navigator.navigateToShippingMap(this, code)
    }

    override fun showException(exceptionMessage: String) {
        alert (exceptionMessage){
            yesButton { finish() }
        }.show()
    }

    override fun goToShippingList() {
        navigator.navigateToShippingList(this)
    }
}
