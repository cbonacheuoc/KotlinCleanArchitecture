package uoc.cbonache.tfg.ui.shippingSign

import uoc.cbonache.tfg.shippings.GetShippingByCodeInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import javax.inject.Inject


/**
 * @author cbonache
 */
class ShippingSignPresenter @Inject constructor(val view: ShippingSignView,
                                                 val getShippingByCodeInteractor: GetShippingByCodeInteractor,
                                                 val exceptionHandler: AndroidExceptionHandler) {

    fun onStart(code: String) {

        getShippingByCodeInteractor.execute(GetShippingByCodeInteractor.Parameters(code)) { result ->
            result.success {
//                shippingSign ->
//                view.showShippingInfo(shippingSign.mapToShippingViewEntity())
            }

            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

}