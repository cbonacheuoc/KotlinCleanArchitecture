package uoc.cbonache.tfg.ui

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import uoc.cbonache.tfg.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author cbonache
 */
@Singleton
class ManagePermissions @Inject constructor() {

    fun setRequestPermissions(activity: Activity, permission: ArrayList<String>, listener: MultiplePermissionsListener) {
        Dexter.withActivity(activity)
                .withPermissions(permission)
                .withListener(listener)
                .check()
    }

    fun setAllPermissionsListener(context: Context, rootView: ViewGroup, message: String,function: () -> Unit): CompositeMultiplePermissionsListener {
        val permissionListener = object : MultiplePermissionsListener {
            override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
            }

            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report != null && report.deniedPermissionResponses.size == 0)
                    function()
            }
        }

        val snackBarPermissionListener = SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
                .with(rootView, context.getString(R.string.edit_permissions))
                .withOpenSettingsButton(context.getString(R.string.settings))
                .withDuration(Snackbar.LENGTH_INDEFINITE)
                .build()

        val dialogPermissionListener = DialogOnAnyDeniedMultiplePermissionsListener.Builder
                .withContext(context)
                .withTitle(context.getString(R.string.permission_dialog_tittle))
                .withMessage(message)
                .withButtonText(android.R.string.ok)
                .build()

        return CompositeMultiplePermissionsListener(permissionListener, snackBarPermissionListener, dialogPermissionListener)
    }
}