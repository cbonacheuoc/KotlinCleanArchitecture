package uoc.cbonache.tfg.ui.main

import android.content.Intent
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var navigator: Navigator

    override fun onRequestLayout(): Int = R.layout.activity_main

    override fun onViewLoaded() {
        presenter.getToken()
    }

    override fun navigateToShippingList() {
        navigator.navigateToShippingList(this, arrayListOf(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun navigateToLoginActivity() {
        navigator.navigateToLoginActivity(this)
        finish()
    }
}

