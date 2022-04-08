package com.example.borutoapp.data.domain.repository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val dataStore: DataStoreOperations) {

    suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.insertPreferences(state)
    }

    fun readBoardingState(): Flow<Boolean>{
        return dataStore.readPreferences()
    }
}