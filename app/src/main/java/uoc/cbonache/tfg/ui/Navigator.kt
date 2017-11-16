package uoc.cbonache.tfg.ui

import android.content.Context
import uoc.cbonache.tfg.ui.login.LoginActivity
import uoc.cbonache.tfg.ui.shippingList.ShippingListActivity
import javax.inject.Inject

/**
 * @author cbonache
 */
class Navigator @Inject constructor() {

    fun navigateToLoginActivity(context: Context){
        val intent = LoginActivity.getIntent(context)
        context.startActivity(intent)
    }

    fun navigateToShippingList(context: Context){
        val intent = ShippingListActivity.getIntent(context)
        context.startActivity(intent)
    }
}