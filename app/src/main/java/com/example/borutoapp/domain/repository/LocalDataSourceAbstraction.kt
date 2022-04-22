package com.example.borutoapp.domain.repository

import com.example.borutoapp.domain.model.Hero

interface LocalDataSourceAbstraction {

    suspend fun getSelectedHero(heroId: Int): Hero
}