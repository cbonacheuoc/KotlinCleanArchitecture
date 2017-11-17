package uoc.cbonache.tfg.ui

import android.content.Context
import uoc.cbonache.tfg.ui.login.LoginActivity
import uoc.cbonache.tfg.ui.shippingList.ShippingListActivity
import javax.inject.Inject

/**
 * @author cbonache
 */
class Navigator @Inject constructor() {

    fun navigateToShippingList(context: Context, flags: ArrayList<Int> = arrayListOf()) {

        val intent = ShippingListActivity.getIntent(context)
        for (flag in flags) {
            intent.addFlags(flag)
        }
        context.startActivity(intent)
    }

    fun navigateToLoginActivity(context: Context){
        val intent = LoginActivity.getIntent(context)
        context.startActivity(intent)
    }

    fun navigateToShippingDetail(context: Context, idShipping: Long) {

    }

}