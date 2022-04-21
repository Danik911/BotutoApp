package com.example.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.Repository
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchHeroesUseCase @Inject constructor(val repository: Repository) {

    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}