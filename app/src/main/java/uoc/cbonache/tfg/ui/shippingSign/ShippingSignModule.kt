package uoc.cbonache.tfg.ui.shippingSign

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class ShippingSignModule {

    @Provides
    internal fun provideShippingSignView(shippingSignActivity: ShippingSignActivity): ShippingSignView {
        return shippingSignActivity
    }
}