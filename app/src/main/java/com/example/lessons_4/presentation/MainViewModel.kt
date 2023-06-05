package com.example.lessons_4.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lessons_4.domain.GetFlowDataUseCase
import com.example.lessons_4.domain.SaveFlowDataUseCase
import com.example.lessons_4.domain.models.SaveUserNameParam
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserNameUseCase: GetFlowDataUseCase,
    private val saveFlowDataUseCase: SaveFlowDataUseCase
) : ViewModel() {

    private val resultLiveDataMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveDataMutable


    suspend fun save(text: String) {
        saveFlowDataUseCase(param = SaveUserNameParam(name = text))
    }

    suspend fun load() {
        getUserNameUseCase().collect {
            resultLiveDataMutable.value = it.firstName + " " + it.lastName
        }
    }


    /*// private val sharedPrefUserStorage by lazy { SharedPrefUserStorage(applicationContext) }

    // private val dataStoreUserStorage by lazy { DataStoreUserStorage(applicationContext) }
    @Inject
    lateinit var dataStoreUserStorage: DataStoreUserStorage


    // private val userRepository by lazy { UserRepositoryImpl(sharedPrefUserStorage) }
    // private val userFlowRepository by lazy { UserFlowRepositoryImpl(dataStoreUserStorage) }
    @Inject
    lateinit var userFlowRepository: UserFlowRepositoryImpl

    // private val getDataUseCase by lazy { GetDataUseCase(userRepository) }
    // private val getFlowDataUseCase by lazy { GetFlowDataUseCase(userFlowRepository) }
    @Inject
    lateinit var getFlowDataUseCase: GetFlowDataUseCase

    // private val saveDataUseCase by lazy { SaveDataUseCase(userRepository) }
    // private val saveFlowDataUseCase by lazy { SaveFlowDataUseCase(userFlowRepository) }
    //@Inject
    //lateinit var saveFlowDataUseCase: SaveFlowDataUseCase
*/

}