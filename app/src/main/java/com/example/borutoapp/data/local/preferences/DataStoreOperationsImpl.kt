package com.example.borutoapp.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.borutoapp.data.domain.repository.DataStoreOperations
import com.example.borutoapp.util.Constants.BORUTO_PREFERENCES_KEY
import com.example.borutoapp.util.Constants.BORUTO_PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStorePref: DataStore<Preferences> by preferencesDataStore(name = BORUTO_PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(BORUTO_PREFERENCES_KEY)
    }

    val dataStore = context.dataStorePref


    override suspend fun insertPreferences(data: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoardingKey]
        }
    }

    override fun readPreferences(): Flow<Boolean> {
        return dataStore.data
            .catch { exeption ->
                if (exeption is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exeption
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoardingKey] ?: false
                onBoardingState

            }
    }
}