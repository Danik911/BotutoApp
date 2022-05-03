package com.example.borutoapp.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.model.HeroRemoteKey
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.BorutoApi
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    val borutoApi: BorutoApi,
    val borutoDatabase: BorutoDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeyDao = borutoDatabase.heroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val maxTimeout = 1440
        val currentTime = System.currentTimeMillis()
        val lastUpdate = heroRemoteKeyDao.getRemoteKey(1)?.lastUpdate ?: 0L
        val timePassed = (currentTime - lastUpdate) / 1000 / 60
        return if (timePassed <= maxTimeout) {
            Log.d("HeroRemoteMediator", parseMillis(currentTime))
            Log.d("HeroRemoteMediator", parseMillis(lastUpdate))
            Log.d("HeroRemoteMediator", "Up to date")
            InitializeAction.SKIP_INITIAL_REFRESH

        } else {
            Log.d("HeroRemoteMediator", parseMillis(currentTime))
            Log.d("HeroRemoteMediator", parseMillis(lastUpdate))
            Log.d("HeroRemoteMediator", "Refresh")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>
    ): RemoteMediator.MediatorResult {

        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage

                }
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextPage
                }
            }

            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {

                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeyDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val lastUpdate = response.lastUpdate

                    val remoteKeys = response.heroes.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdate = lastUpdate
                        )
                    }
                    heroRemoteKeyDao.addRemoteKeys(heroRemoteKeys = remoteKeys)
                    heroDao.insertHeroes(heroes = response.heroes)

                }

            }
            RemoteMediator.MediatorResult.Success(
                endOfPaginationReached = response.nextPage == null
            )

        } catch (e: Exception) {
            return RemoteMediator.MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeysClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { heroId ->
                heroRemoteKeyDao.getRemoteKey(heroId)
            }
        }
    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        val firstPage = state.pages.firstOrNull()
        val firstRemoteKey =
            if (firstPage?.data?.isNotEmpty() == true) {
                firstPage.data.firstOrNull()?.let { hero ->
                    heroRemoteKeyDao.getRemoteKey(heroId = hero.id)
                }
            } else null
        return firstRemoteKey

        /*  return state.pages.firstOrNull() { it?.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
              heroRemoteKeyDao.getRemoteKey(heroId = hero.id)

          }*/

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeyDao.getRemoteKey(heroId = hero.id)
        }
    }

    private fun parseMillis(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
        return format.format(date)
    }
}