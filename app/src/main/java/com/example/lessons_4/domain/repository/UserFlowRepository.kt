package com.example.lessons_4.domain.repository

import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.models.UserName
import kotlinx.coroutines.flow.Flow

interface UserFlowRepository {

    suspend fun saveName(saveParam: SaveUserNameParam): Boolean

    suspend fun getName(): Flow<UserName>

}