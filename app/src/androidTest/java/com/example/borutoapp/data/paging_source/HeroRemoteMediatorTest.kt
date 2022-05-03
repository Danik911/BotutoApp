package com.example.borutoapp.data.paging_source

import androidx.paging.*
import androidx.test.core.app.ApplicationProvider
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.remote.FakeBorutoApi2
import com.example.borutoapp.domain.model.Hero
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var borutoApi: FakeBorutoApi2
    private lateinit var database: BorutoDatabase

    @Before
    fun setup() {
        borutoApi = FakeBorutoApi2()
        database = BorutoDatabase.createDatabase(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun clearDatabase() {
        database.clearAllTables()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadResultSuccessIfMoreDataIsPresent() {
        runBlocking {
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = database
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }
    }
    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadResultSuccessAndEndOfPaginationReachedTrue() {
        runBlocking {
            borutoApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = database
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )
            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)

        }
    }
    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadResultThrowError() {
        runBlocking {

            borutoApi.throwError()
            val remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = database
            )
            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                loadType = LoadType.REFRESH,
                state = pagingState
            )
            assertTrue(result is RemoteMediator.MediatorResult.Error)
        }
    }
}