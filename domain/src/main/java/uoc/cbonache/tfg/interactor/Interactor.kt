package uoc.cbonache.tfg.interactor

import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.async.PostExecutionThread
import uoc.cbonache.tfg.async.doAsync
import uoc.cbonache.tfg.async.onComplete

/**
 * Created by Borja on 1/6/17.
 */
abstract class Interactor<out SuccessValue, in Parameters > constructor(val postExecutionThread: PostExecutionThread) {

    fun execute(parameters: Parameters, delegate: (result: Result<SuccessValue, *>) -> Unit) = doAsync {
        val result = run(parameters)

        onComplete(postExecutionThread) {
            delegate(result)
        }
    }

    abstract fun run(params: Parameters): Result<SuccessValue, *>
}