package uoc.cbonache.tfg.data.dependencyinjection

import uoc.cbonache.tfg.data.dependencyinjection.qualifier.DiskCacheTtl
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.DefaultQueries
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.TokenApiQueries
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.TokenDiskQueries
import uoc.cbonache.tfg.data.network.ApiConstants
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.datasource.SystemTimeProvider
import uoc.cbonache.tfg.data.repository.datasource.TimeProvider
import uoc.cbonache.tfg.data.repository.datasource.WritableDataSource
import uoc.cbonache.tfg.data.repository.query.Query
import uoc.cbonache.tfg.data.repository.token.TokenApiDataSource
import uoc.cbonache.tfg.data.repository.token.TokenDataRepository
import uoc.cbonache.tfg.data.repository.token.TokenDiskDataSource
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import uoc.cbonache.tfg.data.repository.token.query.GetTokenFromDiskQuery
import uoc.cbonache.tfg.data.repository.token.query.LoginApiQuery
import uoc.cbonache.tfg.data.repository.token.query.RefreshTokenApiQuery
import uoc.cbonache.tfg.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Borja on 4/1/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun providesTTLCache(): Long {
        return 60000
    }

    @Provides
    @Singleton
    @DiskCacheTtl
    fun providesTTLDisk(): Long {
        return 60000 * 5
    }

    @Provides
    @Singleton
    fun ProvidesTimeProvider(): TimeProvider {
        return SystemTimeProvider()
    }

    @Provides
    @ElementsIntoSet
    @Singleton
    @DefaultQueries
    fun provideDefaultQueries(): MutableSet<Query> {
        return LinkedHashSet()
    }


    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiConstants.BASEURL)
                    .build()


    @Provides
    @Singleton
    fun providesTokenRepository(tokenDataRepository: TokenDataRepository): TokenRepository {
        return tokenDataRepository
    }

    @Provides
    @Singleton
    fun providesApiTokenReadableDataSource(tokenApiDataSource: TokenApiDataSource): ReadableDataSource<Unit,TokenInfoDataEntity>{
        return tokenApiDataSource
    }

    @Provides
    @Singleton
    fun providesDiskTokenReadableDataSource(tokenDiskDataSource: TokenDiskDataSource): ReadableDataSource<Unit,TokenInfoDataEntity>{
        return tokenDiskDataSource
    }

    @Provides
    @Singleton
    fun providesDiskTokenWritableDataSource(tokenDiskDataSource: TokenDiskDataSource): WritableDataSource<Unit,TokenInfoDataEntity>{
        return tokenDiskDataSource
    }

    @Provides
    @Singleton
    @ElementsIntoSet
    @TokenApiQueries
    fun providesTokenApiQuery(refreshTokenApiQuery: RefreshTokenApiQuery, loginApiQuery: LoginApiQuery): MutableSet<Query> {

        val set = LinkedHashSet<Query>()
        set.add(refreshTokenApiQuery)
        set.add(loginApiQuery)
        return set
    }

    @Provides
    @Singleton
    @ElementsIntoSet
    @TokenDiskQueries
    fun providesTokenDiskQuery(getTokenFromDiskQuery: GetTokenFromDiskQuery): MutableSet<Query> {

        val set = LinkedHashSet<Query>()
        set.add(getTokenFromDiskQuery)
        return set
    }
}
