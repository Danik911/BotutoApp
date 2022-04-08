package com.example.borutoapp.data.domain.use_cases

import com.example.borutoapp.data.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.borutoapp.data.domain.use_cases.save_onboarding.SaveOnBoardingState

data class UseCases(
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val saveReadOnBoardingUseCase: SaveOnBoardingState
)
