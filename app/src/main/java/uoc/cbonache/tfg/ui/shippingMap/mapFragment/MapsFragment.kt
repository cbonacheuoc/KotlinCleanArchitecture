package uoc.cbonache.tfg.ui.shippingMap.mapFragment

import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.base.BaseFragment

/**
 * @author cbonache
 */
class MapsFragment: BaseFragment(), MapsFragmentView {

    override fun onRequestLayout(): Int {
        return R.layout.fragment_map
    }

    override fun onViewLoaded() {
    }
}