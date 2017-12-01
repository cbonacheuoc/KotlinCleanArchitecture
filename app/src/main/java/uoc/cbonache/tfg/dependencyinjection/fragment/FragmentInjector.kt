package uoc.cbonache.tfg.dependencyinjection.fragment

import uoc.cbonache.tfg.ui.main.MainActivityModule
import uoc.cbonache.tfg.ui.shippingMap.mapFragment.MapsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author cbonache
 */
@Module
abstract class FragmentInjector {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun contributeMapsFragmentInjector(): MapsFragment

}