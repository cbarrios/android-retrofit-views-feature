package com.lalosapps.retrofitviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.lalosapps.retrofitviews.databinding.ActivityMainBinding
import com.lalosapps.retrofitviews.network.ApiService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            lifecycleScope.launch {
                val response = ApiService.service.getRandomChuckJoke()
                binding.chuckJoke.text = response.body()?.joke
            }
        }
    }
}