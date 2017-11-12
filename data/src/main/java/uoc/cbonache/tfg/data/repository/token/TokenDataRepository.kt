package uoc.cbonache.tfg.data.repository.token

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.repository.Repository
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import uoc.cbonache.tfg.data.repository.token.model.mapToTokenInfo
import uoc.cbonache.tfg.data.repository.token.query.GetTokenDiskQuery
import uoc.cbonache.tfg.data.repository.token.query.LoginQuery
import uoc.cbonache.tfg.data.repository.token.query.RefreshTokenQuery
import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.repository.TokenRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class TokenDataRepository @Inject constructor(tokenApiDataSource: TokenApiDataSource,
                                              tokenDiskDataSource: TokenDiskDataSource) : TokenRepository, Repository<Unit, TokenInfoDataEntity>() {

    init {
        readableDataSources.add(tokenDiskDataSource)
        readableDataSources.add(tokenApiDataSource)
        writableDataSources.add(tokenDiskDataSource)

    }

    override fun getTokenFromDisk(): Result<TokenInfo, Exception> {

        val params = HashMap<String, Any>()
        val result = query(GetTokenDiskQuery::class.java, params)

        return result.map { it.mapToTokenInfo() }

    }

    override fun refreshToken(token: TokenInfo): Result<TokenInfo, Exception> {

        val params = HashMap<String, Any>()
        params.put(RefreshTokenQuery.REFRESH_TOKEN, token.refresh_token)
        val result = query(RefreshTokenQuery::class.java, params)

        return result.map { it.mapToTokenInfo() }
    }

    override fun login(username: String, password: String): Result<TokenInfo, Exception> {

        val params = HashMap<String, Any>()
        params.put(LoginQuery.Parameters.USERNAME, username)
        params.put(LoginQuery.Parameters.PASSWORD, password)
        val result = query(LoginQuery::class.java, params)

        result.success { value->
            addOrUpdate(value)
        }

        return result.map { it.mapToTokenInfo() }
    }
}