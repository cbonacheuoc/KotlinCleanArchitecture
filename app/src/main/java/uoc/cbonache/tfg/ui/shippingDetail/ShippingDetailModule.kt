package uoc.cbonache.tfg.ui.shippingDetail

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class ShippingDetailModule {

    @Provides
    internal fun provideShippingDetailView(shippingDetailActivity: ShippingDetailActivity): ShippingDetailView {
        return shippingDetailActivity
    }
}