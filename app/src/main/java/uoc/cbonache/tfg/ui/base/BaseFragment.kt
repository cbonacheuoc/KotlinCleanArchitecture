package uoc.cbonache.tfg.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection

/**
 * @author cbonache
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(onRequestLayout(), null)

    abstract fun onRequestLayout(): Int


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidInjection.inject(context as Activity)
        getProvider(context)
    }

    open fun getProvider(context: Context) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onViewLoaded()

    }

    abstract fun onViewLoaded()

    override fun showException(exceptionMessage: String) {
        Snackbar.make(activity.findViewById(android.R.id.content), exceptionMessage, Snackbar.LENGTH_LONG).show()
    }
}