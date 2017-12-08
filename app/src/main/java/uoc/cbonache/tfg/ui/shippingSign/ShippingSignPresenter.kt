package uoc.cbonache.tfg.ui.shippingSign

import uoc.cbonache.tfg.shippings.GetShippingByCodeInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import uoc.cbonache.tfg.shippings.SetShippingStateByIdInteractor
import uoc.cbonache.tfg.ui.model.mapper.mapToShippingViewEntity
import uoc.cbonache.tfg.ui.ShippingStates
import javax.inject.Inject


/**
 * @author cbonache
 */
class ShippingSignPresenter @Inject constructor(val view: ShippingSignView,
                                                 val getShippingByCodeInteractor: GetShippingByCodeInteractor,
                                                 val setShippingStateByIdInteractor: SetShippingStateByIdInteractor,
                                                 val exceptionHandler: AndroidExceptionHandler) {

    fun onStart(code: String) {

        getShippingByCodeInteractor.execute(GetShippingByCodeInteractor.Parameters(code)) { result ->
            result.success {shippingSign ->
                view.signShipping(shippingSign.mapToShippingViewEntity())
            }

            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

    fun onSignButtonPressed(shippingID: Long) {

        setShippingState(shippingID,ShippingStates.DELIVERED.state)
    }

    private fun setShippingState(shippingID: Long, state: Int){

        setShippingStateByIdInteractor.execute(SetShippingStateByIdInteractor.Parameters(shippingID,state)) { result ->
            result.success { result ->
                view.goToShippingList()
            }
            result.failure { exception ->
                exceptionHandler.notifyException(view, exception)
            }
        }
    }

}