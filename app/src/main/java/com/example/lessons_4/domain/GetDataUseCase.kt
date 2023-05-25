package com.example.lessons_4.domain

import com.example.lessons_4.domain.models.UserName
import com.example.lessons_4.domain.repository.UserRepository

class GetDataUseCase(private val userRepository: UserRepository) {

    fun execute(): UserName {
        return userRepository.getName()
    }

}