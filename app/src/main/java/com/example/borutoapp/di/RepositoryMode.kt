package com.example.borutoapp.di

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.data.domain.repository.DataStoreOperations
import com.example.borutoapp.data.domain.repository.Repository
import com.example.borutoapp.data.domain.use_cases.UseCases
import com.example.borutoapp.data.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.example.borutoapp.data.domain.use_cases.save_onboarding.SaveOnBoardingState
import com.example.borutoapp.data.local.preferences.DataStoreOperationsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryMode {


    @Provides
    @Singleton
    fun provideDataPreferences(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            saveReadOnBoardingUseCase = SaveOnBoardingState(repository = repository)
        )
    }

}