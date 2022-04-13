package com.example.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperationsAbstraction {

    suspend fun insertPreferences(data: Boolean)
    fun readPreferences(): Flow<Boolean>
}