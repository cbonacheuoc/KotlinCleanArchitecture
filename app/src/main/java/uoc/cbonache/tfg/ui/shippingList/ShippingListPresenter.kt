package uoc.cbonache.tfg.ui.shippingList

import uoc.cbonache.tfg.model.Shipping
import uoc.cbonache.tfg.shippings.GetShippingsListInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import uoc.cbonache.tfg.ui.model.mapper.mapToShippingViewEntity
import javax.inject.Inject

/**
 * @author cbonache
 */
class ShippingListPresenter  @Inject constructor(val view: ShippingListView,
                                                private val getShippingsListInteractor: GetShippingsListInteractor,
                                                private val exceptionHandler: AndroidExceptionHandler) {

    fun onStart() {
        view.showLoading()
        getShippingsListInteractor.execute(Unit){result ->
            view.hideLoading()
            result.success { shippings ->
                if (shippings.isEmpty()) {
                    view.showNoShippingsWaring()
                } else {
                    view.dismissNoShippingsWarning()
                    view.showShippingList(shippings.map(Shipping::mapToShippingViewEntity))
                }
            }
            result.failure { exception ->
                exceptionHandler.notifyException(view,exception)
            }
        }
    }

    fun onShippingPressed(code: String) {
        view.navigateToShippingDetail(code)
    }

    fun onClickQR() {
        view.scanQR()
    }
}