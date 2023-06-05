package com.example.lessons_4.di

import android.content.Context
import com.example.lessons_4.data.repository.UserFlowRepositoryImpl
import com.example.lessons_4.data.storage.UserFlowStorage
import com.example.lessons_4.data.storage.datastore.DataStoreUserStorage
import com.example.lessons_4.domain.repository.UserFlowRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDataStoreUserStorage(@ApplicationContext context: Context): UserFlowStorage {
        return DataStoreUserStorage(context = context)
    }

    @Provides
    @Singleton
    fun provideUserFlowRepository(userFlowStorage: UserFlowStorage): UserFlowRepository {
        return UserFlowRepositoryImpl(userFlowStorage)
    }

}