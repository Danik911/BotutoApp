package com.example.borutoapp.data.domain.repository

import android.graphics.BitmapFactory
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun insertPreferences(data: Boolean)
    fun readPreferences(): Flow<Boolean>
}