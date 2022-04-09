package com.example.borutoapp.data.domain.use_cases.save_onboarding

import com.example.borutoapp.data.domain.repository.Repository

class SaveOnBoardingState(private val repository: Repository) {

    suspend operator fun invoke(state: Boolean) {
        repository.saveOnBoardingState(state = state)
    }
}