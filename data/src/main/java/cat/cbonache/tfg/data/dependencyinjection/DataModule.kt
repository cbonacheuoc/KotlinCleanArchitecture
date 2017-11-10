package cat.cbonache.tfg.data.dependencyinjection

import cat.cbonache.tfg.data.dependencyinjection.qualifier.DefaultQueries
import cat.cbonache.tfg.data.repository.query.Query
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import javax.inject.Singleton

/**
 * Created by Borja on 4/1/17.
 */
@Module
class DataModule {

    @Provides
    @ElementsIntoSet
    @Singleton
    @DefaultQueries
    fun provideDefaultQueries(): MutableSet<Query> = LinkedHashSet()

}
