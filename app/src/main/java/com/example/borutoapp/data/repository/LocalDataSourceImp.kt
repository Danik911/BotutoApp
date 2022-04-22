package com.example.borutoapp.data.repository

import com.example.borutoapp.data.local.BorutoDatabase
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.LocalDataSourceAbstraction

class LocalDataSourceImp(dataBase: BorutoDatabase) : LocalDataSourceAbstraction {

    private val heroDao = dataBase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.searchHero(heroId = heroId)
    }
}