package uoc.cbonache.tfg.ui.shippingList

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class ShippingListModule {

    @Provides
    internal fun provideShippingListView(shippingListActivity: ShippingListActivity): ShippingListView {
        return shippingListActivity
    }
}