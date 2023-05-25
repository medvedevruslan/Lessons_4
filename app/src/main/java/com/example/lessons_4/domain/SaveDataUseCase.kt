package com.example.lessons_4.domain

import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.repository.UserRepository

class SaveDataUseCase(private val userRepository: UserRepository) {

    fun execute(param: SaveUserNameParam): Boolean {

        val oldUserName = userRepository.getName()
        if (oldUserName.firstName == param.name) {
            return true
        }
        return userRepository.saveName(saveParam = param)
    }
}