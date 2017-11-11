package uoc.cbonache.tfg.repository

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.model.TokenInfo

/**
 * @author cbonache
 */
interface TokenRepository {

    fun getTokenFromDisk(): Result<TokenInfo, Exception>
    fun refreshToken(token: TokenInfo): Result<TokenInfo, Exception>
    fun login(username: String, password: String): Result<TokenInfo, Exception>
}