package com.example.lessons_4.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lessons_4.data.repository.UserRepositoryImpl
import com.example.lessons_4.data.storage.sharedprefs.SharedPrefUserStorage
import com.example.lessons_4.databinding.ActivityMainBinding
import com.example.lessons_4.domain.GetDataUseCase
import com.example.lessons_4.domain.SaveDataUseCase
import com.example.lessons_4.domain.models.SaveUserNameParam
import com.example.lessons_4.domain.models.UserName

class MainActivity : AppCompatActivity() {

    private val sharedPrefUserStorage by lazy { SharedPrefUserStorage(applicationContext) }
    private val userRepository by lazy { UserRepositoryImpl(sharedPrefUserStorage) }
    private val getDataUseCase by lazy { GetDataUseCase(userRepository) }
    private val saveDataUseCase by lazy { SaveDataUseCase(userRepository) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnSave.setOnClickListener {
                val text = editText.text.toString()
                val params = SaveUserNameParam(name = text)
                val result: Boolean = saveDataUseCase.execute(param = params)
                textView.text = "SaveResult = $result"
            }

            btnGet.setOnClickListener {
                val userName: UserName = getDataUseCase.execute()
                textView.text = "${userName.firstName} ${userName.lastName}"
            }
        }
    }
}