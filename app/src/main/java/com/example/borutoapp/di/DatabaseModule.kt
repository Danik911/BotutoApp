package com.example.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.data.repository.LocalDataSourceImp
import com.example.borutoapp.domain.repository.LocalDataSourceAbstraction
import com.example.borutoapp.util.Constants.HERO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BorutoDatabase {
        return Room.databaseBuilder(
            context,
            BorutoDatabase::class.java,
            HERO_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSourceAbstraction {
        return LocalDataSourceImp(dataBase = borutoDatabase)
    }

}