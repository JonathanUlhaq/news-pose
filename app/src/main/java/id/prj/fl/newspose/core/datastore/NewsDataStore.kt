package id.prj.fl.newspose.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class NewsDataStore(private val context: Context) {
    private val Context.newsDataStore: DataStore<Preferences> by preferencesDataStore(name = "news_datastore")

    companion object {
        val SEARCH_HISTORY = stringPreferencesKey("search_history")
    }

    fun getHistoryList(): Flow<List<String>> =
        context.newsDataStore.data.map { preference ->
            preference[SEARCH_HISTORY]
                ?.split("||-")
                ?.filter { it.isNotEmpty() }
                ?: emptyList()
        }

    suspend fun storeHistoryList(list: List<String>) {
        context.newsDataStore.edit { preference ->
            preference[SEARCH_HISTORY] = list.joinToString("||-")
        }
    }
}