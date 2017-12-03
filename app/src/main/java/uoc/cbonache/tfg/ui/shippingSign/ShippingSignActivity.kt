package uoc.cbonache.tfg.ui.shippingSign

import android.content.Context
import android.content.Intent
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
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
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }
}
