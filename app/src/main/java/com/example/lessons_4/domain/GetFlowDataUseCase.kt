package com.example.lessons_4.domain

import com.example.lessons_4.domain.models.UserName
import com.example.lessons_4.domain.repository.UserFlowRepository
import kotlinx.coroutines.flow.Flow

class GetFlowDataUseCase(private val userRepository: UserFlowRepository) {

    suspend operator fun invoke(): Flow<UserName> {
        return userRepository.getName()
    }

}