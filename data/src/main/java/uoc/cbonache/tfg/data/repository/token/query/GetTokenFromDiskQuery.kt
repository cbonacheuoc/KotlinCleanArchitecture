package uoc.cbonache.tfg.data.repository.token.query

import android.content.Context
import android.content.SharedPreferences
import uoc.cbonache.data.basearchitecture.R
import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.ApplicationContext
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import uoc.cbonache.tfg.model.exceptions.TokenException
import javax.inject.Inject

/**
 * @author cbonache
 */
class GetTokenFromDiskQuery @Inject constructor(@ApplicationContext val context: Context): GetTokenDiskQuery{

    private lateinit var sharedPreferences: SharedPreferences
    private val EMPTY = ""

    override fun query(parameters: HashMap<String, *>?, queryable: Any?): Result<*, *> {

        sharedPreferences = getSharedPreferences()

        val token = TokenInfoDataEntity(sharedPreferences.getString(context.getString(R.string.acess_token_key), EMPTY),
                sharedPreferences.getLong(context.getString(R.string.expire_key), 0L),
                sharedPreferences.getString(context.getString(R.string.refresh_token_key), EMPTY),
                sharedPreferences.getLong(context.getString(R.string.retrieve_time), 0L))

        if (token.access_token.isNotEmpty() && token.isTokenStillValid()) {

            return Result.of { token }

        } else if (token.access_token.isNotEmpty() && token.isTokenStillValid()) {

            return Result.Failure(TokenException())
        }
        return Result.Failure()
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.TOKEN_FILE), Context.MODE_PRIVATE)
    }
}