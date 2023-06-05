package com.example.lessons_4.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lessons_4.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.resultLive.observe(this) {
            Log.d("developer", it)
            binding.textView.text = it
        }

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            btnSave.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Default) {
                    viewModel.save(editText.text.toString())
                }
            }

            btnGet.setOnClickListener {
                lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.load()
                }
            }
        }
    }
}