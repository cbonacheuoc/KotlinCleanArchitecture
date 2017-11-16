package uoc.cbonache.tfg

import android.os.Handler
import android.os.Looper
import uoc.cbonache.tfg.async.PostExecutionThread


class UiThread constructor(val handler: Handler = Handler(Looper.getMainLooper())) : PostExecutionThread
{
    override fun <T> submit(function: () -> T?) {
        handler.post {
            function.invoke()
        }
    }

}