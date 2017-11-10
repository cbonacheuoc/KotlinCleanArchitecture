package cat.cbonache.tfg.ui.main

import cat.cbonache.tfg.R
import cat.cbonache.tfg.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject lateinit var presenter: MainPresenter

    override fun onRequestLayout(): Int = R.layout.activity_main

    override fun onViewLoaded() {
        presenter.pastel()
    }

}

