package uoc.cbonache.tfg.ui.shippingList

import uoc.cbonache.tfg.ui.base.LoadingView
import uoc.cbonache.tfg.ui.model.ShippingViewEntity

/**
 * @author cbonache
 */
interface ShippingListView : LoadingView {

    fun showShippingList(shippingsList: List<ShippingViewEntity>)
    fun showNoShippingsWaring()
    fun dismissNoShippingsWarning()
    fun navigateToShippingDetail(idShipping: Long)

}