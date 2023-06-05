package com.example.lessons_4.di

import com.example.lessons_4.domain.GetFlowDataUseCase
import com.example.lessons_4.domain.SaveFlowDataUseCase
import com.example.lessons_4.domain.repository.UserFlowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetFlowDataUseCase(userFlowRepository: UserFlowRepository): GetFlowDataUseCase {
        return GetFlowDataUseCase(userFlowRepository)
    }

    @Provides
    fun provideSaveFlowDataUseCase(userFlowRepository: UserFlowRepository): SaveFlowDataUseCase {
        return SaveFlowDataUseCase(userFlowRepository)
    }

}