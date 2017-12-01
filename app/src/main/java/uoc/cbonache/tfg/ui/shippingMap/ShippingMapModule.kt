package uoc.cbonache.tfg.ui.shippingMap

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class ShippingMapModule {

    @Provides
    internal fun provideShippingMapView(shippingMapActivity: ShippingMapActivity): ShippingMapView {
        return shippingMapActivity
    }
}