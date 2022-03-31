package com.example.lessons_4.data.repository

import com.example.lessons_4.data.storage.models.User
import com.example.lessons_4.data.storage.UserStorage
import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.models.UserName
import com.example.lessons_4.domain.repository.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage): UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {

        val user = User(firstName =  saveParam.name, lastName = "")

        return userStorage.save(user)
    }

    override fun getName(): UserName {

        val user = userStorage.get()

        return UserName(firstName = user.firstName, lastName = user.lastName)
    }
}