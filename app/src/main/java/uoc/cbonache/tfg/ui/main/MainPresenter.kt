package uoc.cbonache.tfg.ui.main

import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.token.GetTokenInteractor
import uoc.cbonache.tfg.token.RefreshTokenInteractor
import javax.inject.Inject

/**
 * @author cbonache
 */
class MainPresenter @Inject constructor(val view: MainView,
                                        val getTokenInteractor: GetTokenInteractor,
                                        val refreshTokenInteractor: RefreshTokenInteractor) {
    fun getToken() {
        getTokenInteractor.execute(Unit) { result ->
            result.success { value ->
                if (value.isTokenStillValid()) view.navigateToShippingList()
                else refreshToken(value)
            }
            result.failure { exception ->
                //TODO: Go to login Activity because we haven't access_token or refresh_token
                view.navigateToLoginActivity()
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
