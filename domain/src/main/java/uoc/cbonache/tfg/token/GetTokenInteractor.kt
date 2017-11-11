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
class GetTokenInteractor @Inject constructor(postExecutionThread: PostExecutionThread,
                                             val repository: TokenRepository)
    : Interactor<TokenInfo, Unit>(postExecutionThread) {

    override fun run(params: Unit): Result<TokenInfo, *> {
        return repository.getTokenFromDisk()
    }


}