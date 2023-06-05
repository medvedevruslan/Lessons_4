package com.example.lessons_4.data.storage.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lessons_4.data.storage.UserFlowStorage
import com.example.lessons_4.data.storage.models.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val KEY_FIRST_NAME = "first_name"
private const val KEY_LAST_NAME = "last_name"

private const val USER_PREFERENCES_NAME = "user_preferences"
private val DATA_KEY_FIRST_NAME = stringPreferencesKey(KEY_FIRST_NAME)
private val DATA_KEY_LAST_NAME = stringPreferencesKey(KEY_LAST_NAME)

class DataStoreUserStorage @Inject constructor(@ApplicationContext context: Context) :
    UserFlowStorage {

    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES_NAME)
    private val userStore = context.dataStore

    override suspend fun save(user: User): Boolean {
        userStore.edit { preferences ->
            preferences[DATA_KEY_FIRST_NAME] = user.firstName
            preferences[DATA_KEY_LAST_NAME] = user.lastName
        }
        return true
    }

    override suspend fun get(): Flow<User> {
        return userStore.data.map { preferences ->
            User(
                preferences[DATA_KEY_FIRST_NAME].toString(),
                preferences[DATA_KEY_LAST_NAME].toString()
            )
        }
    }
}