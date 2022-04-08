package com.example.borutoapp.data.domain.use_cases.read_onboarding

import com.example.borutoapp.data.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(private val repository: Repository) {

    fun invoke(): Flow<Boolean> {
        return repository.readBoardingState()
    }
}