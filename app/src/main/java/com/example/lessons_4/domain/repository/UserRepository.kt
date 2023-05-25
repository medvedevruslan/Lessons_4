package com.example.lessons_4.domain.repository

import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.models.UserName

interface UserRepository {

    fun saveName(saveParam: SaveUserNameParam): Boolean

    fun getName(): UserName

}