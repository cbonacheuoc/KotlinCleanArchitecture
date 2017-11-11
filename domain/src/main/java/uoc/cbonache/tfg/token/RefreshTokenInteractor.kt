package uoc.cbonache.tfg.token

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.interactor.Interactor
import uoc.cbonache.tfg.model.TokenInfo
import uoc.cbonache.tfg.repository.TokenRepository
import javax.inject.Inject

/**
 * @author cbonache
 */
class RefreshTokenInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                                 val repository: TokenRepository)
    : Interactor<TokenInfo, RefreshTokenInteractor.Parameters>(postExecutionThread) {

    override fun run(params: RefreshTokenInteractor.Parameters): Result<TokenInfo, *> {
        val result = repository.refreshToken(params.token)
        return result
    }
    data class Parameters(var token: TokenInfo)
}