package com.example.lessons_4.data.storage

import com.example.lessons_4.data.storage.models.User


interface UserStorage {

    fun save(user: User): Boolean

    fun get(): User
}