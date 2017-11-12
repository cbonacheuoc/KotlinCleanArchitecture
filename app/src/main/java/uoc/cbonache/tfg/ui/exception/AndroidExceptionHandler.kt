package uoc.cbonache.tfg.ui.exception

import android.content.Context
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.ApplicationContext
import uoc.cbonache.tfg.model.exceptions.NetworkException
import uoc.cbonache.tfg.ui.base.BaseView
import dagger.Reusable
import javax.inject.Inject

/**
 * @author cbonache
 */
@Reusable
class AndroidExceptionHandler @Inject constructor(@ApplicationContext val context: Context) : ExceptionHandler {

    override fun<T: BaseView> notifyException(view: T, exception: Exception?) {
        when (exception) {

            is NetworkException.UnauthorizedException ->  view.showException(context.getString(R.string.unauthorized))
            is NetworkException.NoInternetConnection -> view.showException(context.getString(R.string.no_internet_connection))
            is NetworkException.ServerException -> view.showException(context.getString(R.string.server_error))
            else -> view.showException(exception?.message ?: "Unknown error")

        }
    }
}