package com.example.borutoapp.domain.use_cases

import com.example.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.example.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingState

data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveReadOnBoardingUseCase: SaveOnBoardingState,
    val getAllHeroesUseCase: GetAllHeroesUseCase
)
