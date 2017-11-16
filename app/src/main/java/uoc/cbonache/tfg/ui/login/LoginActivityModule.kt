package uoc.cbonache.tfg.ui.login

import dagger.Module
import dagger.Provides

/**
 * @author cbonache
 */
@Module
class LoginActivityModule {

    @Provides
    internal fun provideLoginView(loginActivity: LoginActivity): LoginView {
        return loginActivity
    }
}