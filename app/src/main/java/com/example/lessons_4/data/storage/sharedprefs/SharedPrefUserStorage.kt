package com.example.lessons_4.data.storage.sharedprefs

import android.content.Context
import com.example.lessons_4.data.storage.UserStorage
import com.example.lessons_4.data.storage.models.User

private const val SHARED_PREFS_NAME = "shared_pref_name"
private const val KEY_FIRST_NAME = "first_name"
private const val KEY_LAST_NAME = "last_name"
private const val DEFAULT_LAST_NAME = "DefaultLastName"
private const val DEFAULT_FIRST_NAME = "DefaultFirstName"

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun save(user: User): Boolean {
        return (sharedPreferences.edit().putString(KEY_FIRST_NAME, user.firstName).commit() &&
                sharedPreferences.edit().putString(KEY_LAST_NAME, user.lastName).commit())
    }

    override fun get(): User {

        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, DEFAULT_FIRST_NAME) ?: DEFAULT_FIRST_NAME
        val lastName = sharedPreferences.getString(KEY_LAST_NAME, DEFAULT_LAST_NAME) ?: DEFAULT_LAST_NAME

        return User(firstName, lastName)
    }
}