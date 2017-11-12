package uoc.cbonache.tfg.data.repository.token

import android.content.Context
import android.content.SharedPreferences
import uoc.cbonache.data.basearchitecture.R
import uoc.cbonache.tfg.Result
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.ApplicationContext
import uoc.cbonache.tfg.data.dependencyinjection.qualifier.queries.TokenDiskQueries
import uoc.cbonache.tfg.data.repository.datasource.ReadableDataSource
import uoc.cbonache.tfg.data.repository.datasource.WritableDataSource
import uoc.cbonache.tfg.data.repository.query.Query
import uoc.cbonache.tfg.data.repository.token.model.TokenInfoDataEntity
import javax.inject.Inject

/**
 * @author cbonache
 */
class TokenDiskDataSource @Inject constructor(@TokenDiskQueries override val queries: MutableSet<Query>, @ApplicationContext val context: Context)
    : ReadableDataSource<Unit, TokenInfoDataEntity>, WritableDataSource<Unit, TokenInfoDataEntity> {

    private lateinit var sharedPreferences: SharedPreferences
    private val EMPTY = ""

    override fun addOrUpdate(value: TokenInfoDataEntity): Result<TokenInfoDataEntity, *> {

        sharedPreferences = getSharedPreferences()
        val editor = sharedPreferences.edit()
        editor.putString(context.getString(R.string.acess_token_key), value.access_token)
        editor.putString(context.getString(R.string.refresh_token_key), value.refresh_token)
        editor.putLong(context.getString(R.string.expire_key), value.expires_in)
        editor.putLong(context.getString(R.string.retrieve_time), value.tokenRetrievalDateInMillis)
        editor.apply()
        return Result.of { value }
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.TOKEN_FILE), Context.MODE_PRIVATE)
    }
}