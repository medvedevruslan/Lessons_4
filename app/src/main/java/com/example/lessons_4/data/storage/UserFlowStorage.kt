package com.example.lessons_4.data.storage

import com.example.lessons_4.data.storage.models.User
import kotlinx.coroutines.flow.Flow

interface UserFlowStorage {

    suspend fun save(user: User): Boolean

    suspend fun get(): Flow<User>

}