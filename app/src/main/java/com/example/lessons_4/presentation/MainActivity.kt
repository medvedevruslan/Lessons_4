package com.example.lessons_4.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lessons_4.data.repository.UserFlowRepositoryImpl
import com.example.lessons_4.data.repository.UserRepositoryImpl
import com.example.lessons_4.data.storage.datastore.DataStoreUserStorage
import com.example.lessons_4.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.lessons_4.databinding.ActivityMainBinding
import com.example.lessons_4.domain.GetDataUseCase
import com.example.lessons_4.domain.GetFlowDataUseCase
import com.example.lessons_4.domain.SaveDataUseCase
import com.example.lessons_4.domain.SaveFlowDataUseCase
import com.example.lessons_4.domain.models.SaveUserNameParam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val sharedPrefUserStorage by lazy { SharedPrefUserStorage(applicationContext) }
    private val dataStoreUserStorage by lazy { DataStoreUserStorage(applicationContext) }

    private val userRepository by lazy { UserRepositoryImpl(sharedPrefUserStorage) }
    private val userFlowRepository by lazy { UserFlowRepositoryImpl(dataStoreUserStorage) }

    private val getDataUseCase by lazy { GetDataUseCase(userRepository) }
    private val getFlowDataUseCase by lazy { GetFlowDataUseCase(userFlowRepository) }

    private val saveDataUseCase by lazy { SaveDataUseCase(userRepository) }
    private val saveFlowDataUseCase by lazy { SaveFlowDataUseCase(userFlowRepository) }


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnSave.setOnClickListener {
                scope.launch {
                    val text = editText.text.toString()
                    val params = SaveUserNameParam(name = text)
                    val result: Boolean = saveFlowDataUseCase(param = params)
                    textView.text = "SaveResult = $result"
                }
            }

            btnGet.setOnClickListener {
                scope.launch(Dispatchers.Main) {
                    getFlowDataUseCase().collect { userName ->
                        Log.d("developer", " collect: ${userName.firstName} | ${userName.lastName}")
                        textView.text = "${userName.firstName} ${userName.lastName}"
                    }
                }
            }
        }
    }
}