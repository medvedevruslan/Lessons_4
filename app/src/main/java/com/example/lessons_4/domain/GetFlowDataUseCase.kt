package com.example.lessons_4.domain

import com.example.lessons_4.domain.models.UserName
import com.example.lessons_4.domain.repository.UserFlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFlowDataUseCase @Inject constructor(private val userRepository: UserFlowRepository) {

    suspend operator fun invoke(): Flow<UserName> {
        return userRepository.getName()
    }

}