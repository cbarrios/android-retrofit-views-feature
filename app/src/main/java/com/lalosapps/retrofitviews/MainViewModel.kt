package com.lalosapps.retrofitviews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lalosapps.retrofitviews.network.ApiService
import com.lalosapps.retrofitviews.network.ChuckJoke
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _chuckJoke = MutableLiveData<ChuckJoke?>(null)
    val chuckJoke: LiveData<ChuckJoke?> = _chuckJoke

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage


    init {
        getRandomChuckJoke()
    }

    private var job: Job? = null
    fun getRandomChuckJoke() {
        job?.cancel()
        job = viewModelScope.launch {
            try {
                _loading.value = true
                val response = ApiService.service.getRandomChuckJoke()
                if (response.isSuccessful) {
                    _chuckJoke.value = response.body()
                } else {
                    _errorMessage.value = "Error code: ${response.code()}"
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                _errorMessage.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun onErrorMessageShown() {
        _errorMessage.value = null
    }
}