package com.example.lessons_4.data.repository

import com.example.lessons_4.data.storage.UserFlowStorage
import com.example.lessons_4.data.storage.models.User
import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.models.UserName
import com.example.lessons_4.domain.repository.UserFlowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserFlowRepositoryImpl(private val userFlowStorage: UserFlowStorage) : UserFlowRepository {

    override suspend fun saveName(saveParam: SaveUserNameParam): Boolean {

        val list = saveParam.name.split("\\s".toRegex())


        val user = User(firstName = list.first(), lastName = list.last())
        return userFlowStorage.save(user)
    }

    override suspend fun getName(): Flow<UserName> {
        return userFlowStorage.get().map {
            UserName(firstName = it.firstName, lastName = it.lastName)
        }
    }
}