package com.example.borutoapp.data.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.DataStoreOperationsAbstraction
import com.example.borutoapp.domain.repository.RemoteDataSourceAbstraction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreAbstraction: DataStoreOperationsAbstraction,
    private val remoteDataSourceAbstraction: RemoteDataSourceAbstraction
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSourceAbstraction.getAllHeroes()
    }

    suspend fun saveOnBoardingState(state: Boolean) {
        dataStoreAbstraction.insertPreferences(state)
    }

    fun readBoardingState(): Flow<Boolean> {
        return dataStoreAbstraction.readPreferences()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteDataSourceAbstraction.searchHero(query = query)
    }
}