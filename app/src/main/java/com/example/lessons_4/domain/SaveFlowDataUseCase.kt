package com.example.lessons_4.domain

import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.repository.UserFlowRepository
import javax.inject.Inject

class SaveFlowDataUseCase @Inject constructor(private val userRepository: UserFlowRepository) {

    suspend operator fun invoke(param: SaveUserNameParam): Boolean {
        return userRepository.saveName(saveParam = param)
    }
}