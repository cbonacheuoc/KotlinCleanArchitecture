package cat.cbonache.tfg.dependencyinjection.activity

import cat.cbonache.tfg.ui.main.MainActivity
import cat.cbonache.tfg.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Borja on 17/7/17.
 */

@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity
}
