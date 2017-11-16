package uoc.cbonache.tfg.ui.login

import uoc.cbonache.tfg.ui.base.LoadingView

/**
 * @author cbonache
 */
interface LoginView: LoadingView {
    fun getUserName(): String
    fun getPassword(): String
    fun showPasswordError()
    fun showUsernameError()
    fun navigateToShippingList()

}