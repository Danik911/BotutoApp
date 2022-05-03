package com.example.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.BorutoApi
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.data.paging_source.HeroRemoteMediator
import com.example.borutoapp.data.paging_source.SearchHeroSource
import com.example.borutoapp.domain.repository.RemoteDataSourceAbstraction
import com.example.borutoapp.util.Constants.ITEMS_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class RemoteDataSourceImpl
    (
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase
) : RemoteDataSourceAbstraction {

    val heroDao = borutoDatabase.heroDao()


    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagerFactory = { heroDao.getAllHeroes() }

        return Pager(
            config = PagingConfig(pageSize = ITEMS_PAGE_SIZE),
            pagingSourceFactory = pagerFactory,
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            )
        ).flow
    }

    override fun searchHero(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PAGE_SIZE),
            pagingSourceFactory = {
                SearchHeroSource(
                    borutoApi = borutoApi,
                    searchQuery = query
                )
            }
        ).flow
    }
}