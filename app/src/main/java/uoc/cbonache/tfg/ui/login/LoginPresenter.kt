package uoc.cbonache.tfg.ui.login

import uoc.cbonache.tfg.token.LoginInteractor
import uoc.cbonache.tfg.ui.exception.AndroidExceptionHandler
import javax.inject.Inject

/**
 * @author cbonache
 */
class LoginPresenter @Inject constructor(val view: LoginView,
                                         val loginInteractor: LoginInteractor,
                                         val exceptionHandler: AndroidExceptionHandler) {

    fun login(username: String, password: String) {

        if(checkFields(username,password)){

            view.showLoading()
            loginInteractor.execute(LoginInteractor.Parameters(username,password)) { result ->
                view.hideLoading()

                result.success { value ->

                    view.navigateToShippingList()

                }
                result.failure { exception ->
                    exceptionHandler.notifyException(view, exception)
                }
            }
        }


    }

    fun onLoginButtonPressed() {

        val username = view.getUserName()
        val password = view.getPassword()

        login(username,password)
    }

    private fun checkFields(username: String, password: String): Boolean {

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            validateErrors()
            return false
        }

        return true
    }

    fun validateErrors() {

        if(view.getPassword().trim().isEmpty()) view.showPasswordError()
        if(view.getUserName().trim().isEmpty()) view.showUsernameError()    }
}
