package uoc.cbonache.tfg.ui.login

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.Navigator
import uoc.cbonache.tfg.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.singleTop
import javax.inject.Inject

/**
 * @author cbonache
 */
class LoginActivity : BaseActivity(), LoginView {

    companion object {

        @JvmStatic
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            intent.clearTop()
            intent.singleTop()
            return intent
        }
    }

    @Inject lateinit var presenter: LoginPresenter
    @Inject lateinit var navigator: Navigator

    override fun onRequestLayout(): Int {
        return R.layout.activity_login
    }

    override fun onViewLoaded() {


        loginButton.setOnClickListener {

            presenter.onLoginButtonPressed()
        }

        userWrapper.editText?.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }
            override fun afterTextChanged(s: Editable) {
                val errorMessage = userWrapper.error
                errorMessage?.let {
                    if(s.toString().isNotEmpty() && errorMessage.isNotEmpty()){
                        userWrapper.error = null
                    }
                }
            }
        })


        passwordWrapper.editText?.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }
            override fun afterTextChanged(s: Editable) {
                val errorMessage = passwordWrapper.error
                errorMessage?.let {
                    if(s.toString().isNotEmpty() && errorMessage.isNotEmpty()){
                        passwordWrapper.error = null
                    }
                }
            }
        })
    }

    override fun getUserName(): String = userWrapper.editText?.text.toString()

    override fun getPassword(): String = passwordWrapper.editText?.text.toString()

    override fun showPasswordError() {
        passwordWrapper.isErrorEnabled = false
        passwordWrapper.error = getString(R.string.password_error)
        password.requestFocus()    }

    override fun showUsernameError() {
        userWrapper.isErrorEnabled = false
        userWrapper.error = getString(R.string.username_error)
        username.requestFocus()
    }

    override fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading.visibility = View.GONE
    }

    override fun navigateToShippingList() {
        navigator.navigateToShippingList(this)
    }
}
