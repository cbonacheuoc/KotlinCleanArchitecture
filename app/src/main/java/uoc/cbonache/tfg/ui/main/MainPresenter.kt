package uoc.cbonache.tfg.ui.main

import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.token.GetTokenInteractor
import uoc.cbonache.tfg.token.RefreshTokenInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import javax.inject.Inject

/**
 * Created by Borja on 17/7/17.
 */

class MainPresenter @Inject constructor(val view: MainView,
                                        val getTokenInteractor: GetTokenInteractor,
                                        val refreshTokenInteractor: RefreshTokenInteractor,
                                        val exceptionHandler: AndroidExceptionHandler) {

    fun getToken() {


        getTokenInteractor.execute(Unit) { result ->

            result.success { value ->


                if (value.isTokenStillValid()) view.navigateToShippingList()
                else refreshToken(value)

            }
            result.failure { exception ->
                //TODO: Go to login Activity because we haven't access_token or refresh_token
                view.navigateToLoginActivity()
//                exceptionHandler.notifyException(view, exception)
            }
        }

    }

    fun refreshToken(tokenInfo: TokenInfo) {

        refreshTokenInteractor.execute(RefreshTokenInteractor.Parameters(tokenInfo)) { result ->

            result.success {
                view.navigateToShippingList()
            }

            result.failure { view.navigateToLoginActivity() }
        }
    }
}
