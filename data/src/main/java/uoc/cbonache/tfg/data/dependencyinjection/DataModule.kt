package uoc.cbonache.tfg.data.dependencyinjection

import uoc.cbonache.tfg.data.dependencyinjection.qualifier.DiskCacheTtl
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.MapsRetrofit
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.*
import uoc.cbonache.tfg.data.network.ApiConstants
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.datasource.SystemTimeProvider
import uoc.cbonache.tfg.data.repository.datasource.TimeProvider
import uoc.cbonache.tfg.data.repository.datasource.WritableDataSource
import uoc.cbonache.tfg.data.repository.shippingRepository.ShippingApiDataSource
import uoc.cbonache.tfg.data.repository.shippingRepository.ShippingDataRepository
import uoc.cbonache.tfg.data.repository.shippingRepository.model.ShippingDataEntity
import uoc.cbonache.tfg.data.repository.shippingRepository.query.GetShippingByIdQueryApi
import uoc.cbonache.tfg.data.repository.shippingRepository.query.GetShippingsQueryApi
import uoc.cbonache.tfg.data.repository.query.Query
import uoc.cbonache.tfg.data.repository.route.RouteApiDataSource
import uoc.cbonache.tfg.data.repository.route.RouteDataRepository
import uoc.cbonache.tfg.data.repository.route.model.StepDataEntity
import uoc.cbonache.tfg.data.repository.route.query.GetRouteQueryApi
import uoc.cbonache.tfg.data.repository.token.TokenApiDataSource
import uoc.cbonache.tfg.data.repository.token.TokenDataRepository
import uoc.cbonache.tfg.data.repository.token.TokenDiskDataSource
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import uoc.cbonache.tfg.data.repository.token.query.GetTokenFromDiskQuery
import uoc.cbonache.tfg.data.repository.token.query.LoginApiQuery
import uoc.cbonache.tfg.data.repository.token.query.RefreshTokenApiQuery
import uoc.cbonache.tfg.repository.ShippingsRepository
import uoc.cbonache.tfg.repository.RouteRepository
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
/**
 * @author cbonache
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
    @MapsRetrofit
    fun providesMapsRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(ApiConstants.BASE_MAPS)
                    .build()


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

    @Provides
    @Singleton
    fun providesShippingRepository(shippingDataRepository: ShippingDataRepository): ShippingsRepository {
        return shippingDataRepository
    }

    @Provides
    @Singleton
    fun providesApiShippingReadableDataSource(shippingApiDataSource: ShippingApiDataSource): ReadableDataSource<String,ShippingDataEntity>{
        return shippingApiDataSource
    }

    @Provides
    @Singleton
    @ElementsIntoSet
    @ShippingsListQueries
    fun providesShippingsApiQuery(getShippingsQuery: GetShippingsQueryApi, getShippingByIdQuery: GetShippingByIdQueryApi): MutableSet<Query> {

        val set = LinkedHashSet<Query>()
        set.add(getShippingsQuery)
        set.add(getShippingByIdQuery)
        return set
    }


    @Provides
    @Singleton
    fun providesRouteRepository(routeDataRepository: RouteDataRepository): RouteRepository {
        return routeDataRepository
    }

    @Provides
    @Singleton
    fun providesApiRouteReadableDataSource(routeApiDataSource: RouteApiDataSource): ReadableDataSource<Unit,StepDataEntity>{
        return routeApiDataSource
    }

    @Provides
    @Singleton
    @ElementsIntoSet
    @StepsListQueries
    fun providesStepsApiQuery(getRouteQueryApi: GetRouteQueryApi): MutableSet<Query> {

        val set = LinkedHashSet<Query>()
        set.add(getRouteQueryApi)
        return set
    }
}