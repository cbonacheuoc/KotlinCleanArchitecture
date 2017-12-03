package uoc.cbonache.tfg.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import uoc.cbonache.tfg.ui.login.LoginActivity
import uoc.cbonache.tfg.ui.shippingList.ShippingListActivity
import uoc.cbonache.tfg.ui.shippingDetail.ShippingDetailActivity
import uoc.cbonache.tfg.ui.shippingSign.ShippingSignActivity
import uoc.cbonache.tfg.ui.shippingMap.ShippingMapActivity
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

    fun navigateToShippingDetail(context: Context, code: String) {
        val intent = ShippingDetailActivity.getIntent(context)
        intent.putExtra(ShippingDetailActivity.CODE,code)
        context.startActivity(intent)
    }

    fun navigateToShippingSign(context: Context, code: String) {
        val intent = ShippingSignActivity.getIntent(context)
        intent.putExtra(ShippingSignActivity.CODE,code)
        context.startActivity(intent)
    }

    fun navigateToShippingMap(context: Context, code: String) {
        val intent = ShippingMapActivity.getIntent(context)
        intent.putExtra(ShippingMapActivity.CODE,code)
        context.startActivity(intent)
    }

    fun navigateToGoogleMapsApps(context: Context, destination: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + destination))
        context.startActivity(intent)
    }

}