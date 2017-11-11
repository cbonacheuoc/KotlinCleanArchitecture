package uoc.cbonache.tfg.dependencyinjection.application

import android.app.Application
import android.content.Context
import uoc.cbonache.tfg.UiThread
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Borja on 21/12/16.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun providesPostExecutionThread() : PostExecutionThread = UiThread()
}
