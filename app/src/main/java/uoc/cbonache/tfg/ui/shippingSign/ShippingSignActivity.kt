package uoc.cbonache.tfg.ui.shippingSign

import android.content.Context
import android.content.Intent
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import kotlinx.android.synthetic.main.activity_shipping_sign.*
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingSignActivity: BaseActivity(), ShippingSignView {

    companion object {

        const val CODE = "code"
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, ShippingSignActivity::class.java)
        }
    }

    @Inject lateinit var presenter: ShippingSignPresenter
    @Inject lateinit var navigator: Navigator

    override fun onRequestLayout(): Int {
        return R.layout.activity_shipping_sign
    }

    override fun onViewLoaded() {
        val code = intent.extras.get(CODE) as String
        setUpActionBar()
        presenter.onStart(code)
    }

    private fun setUpActionBar() {

        supportActionBar?.apply {
            title = getString(R.string.shipping_sign)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun signShipping(shippingViewEntity: ShippingViewEntity) {
        buttonSign.setOnClickListener {
            presenter.onSignButtonPressed(shippingViewEntity.id)
        }
    }

    override fun goToShippingList() {
        navigator.navigateToShippingList(this)
    }
}
