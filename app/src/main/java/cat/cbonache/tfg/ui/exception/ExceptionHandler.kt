package cat.cbonache.tfg.ui.exception

import cat.cbonache.tfg.ui.base.BaseView

/**
 * Created by Borja on 23/5/17.
 */
interface ExceptionHandler {
    fun <T : BaseView> notifyException(view: T, exception: Exception?)
}
