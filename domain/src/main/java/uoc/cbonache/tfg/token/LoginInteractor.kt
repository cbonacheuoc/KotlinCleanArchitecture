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
class LoginInteractor  @Inject constructor(postExecutionThread: PostExecutionThread,
                                           val repository: TokenRepository)
    : Interactor<TokenInfo, LoginInteractor.Parameters>(postExecutionThread) {

    override fun run(params: Parameters): Result<TokenInfo, *> {
        return repository.login(params.username,params.password)
    }

    data class Parameters(val username: String, val password: String)


}