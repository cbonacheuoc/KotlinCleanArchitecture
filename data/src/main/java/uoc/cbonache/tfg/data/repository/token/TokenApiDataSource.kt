package uoc.cbonache.tfg.data.repository.token

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.TokenApiQueries
import uoc.cbonache.tfg.data.network.ConnectionChecker
import uoc.cbonache.tfg.data.network.parseResponse
import uoc.cbonache.tfg.data.network.service.TokenService
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.query.Query
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import uoc.cbonache.tfg.model.Credential
import uoc.cbonache.tfg.model.exceptions.NetworkException
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @author cbonache
 */
class TokenApiDataSource @Inject constructor(@TokenApiQueries override val queries: MutableSet<Query>,
                                             val retrofit: Retrofit,
                                             val connectionChecker: ConnectionChecker)
    : ReadableDataSource<Unit, TokenInfoDataEntity> {

    override fun getByKey(params: Unit): Result<TokenInfoDataEntity, *> {

        if (connectionChecker.thereIsConnectivity()) {

            val tokenService = retrofit.create(TokenService::class.java)
            val credentials = Credential("password", 2, "lLyn41lAKPzLSgko2LegicH6duSc3160i6gzH5ep", "cbonache@uoc.edu", "cbonache")
            val response = tokenService.getToken(credentials.grant_type, credentials.client_id.toString(), credentials.client_secret, credentials.username, credentials.password).execute()

            if (response.isSuccessful) {
                val result = response.parseResponse<TokenInfoDataEntity>("").map {
                    it.setCurrentTimeAsRetrievalDate()
                    it
                }
                return result
            }
            return Result.Failure()
        }
        return Result.Failure(NetworkException.NoInternetConnection())
    }
}