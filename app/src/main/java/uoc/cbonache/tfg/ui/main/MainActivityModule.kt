package uoc.cbonache.tfg.ui.main

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainView(mainActivity: MainActivity): MainView {
        return mainActivity
    }
}
