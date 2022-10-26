package com.lalosapps.retrofitviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.lalosapps.retrofitviews.databinding.ActivityMainBinding
import com.lalosapps.retrofitviews.util.snack

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()

        binding.root.setOnClickListener {
            viewModel.getRandomChuckJoke()
        }
    }

    private fun setupObservers() {
        viewModel.loading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.chuckJoke.observe(this) {
            it?.let { chuckJoke ->
                binding.chuckJoke.text = chuckJoke.joke
            }
        }
        viewModel.errorMessage.observe(this) {
            it?.let { errorMessage ->
                binding.root.snack(errorMessage)
                viewModel.onErrorMessageShown()
            }
        }
    }
}