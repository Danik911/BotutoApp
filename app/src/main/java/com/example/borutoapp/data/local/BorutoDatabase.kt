package com.example.borutoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.model.HeroRemoteKey
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao


@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {

    companion object {
        fun createDatabase(context: Context, useInMemory: Boolean): BorutoDatabase {
            val database = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(
                    context,
                    BorutoDatabase::class.java
                )
            } else {
                Room.databaseBuilder(
                    context,
                    BorutoDatabase::class.java,
                    "testDatabase"
                )
            }
            return database
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}