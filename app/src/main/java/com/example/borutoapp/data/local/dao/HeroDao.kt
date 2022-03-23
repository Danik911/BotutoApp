package com.example.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.borutoapp.data.domain.model.Hero
import retrofit2.http.DELETE


@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_database_table ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_database_table WHERE id=:heroId")
    fun searchHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(list: List<Hero>)

    @Query("DELETE FROM hero_database_table")
    fun deleteAllHeroes()
}