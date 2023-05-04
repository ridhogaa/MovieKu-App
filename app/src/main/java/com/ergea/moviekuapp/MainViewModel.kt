package com.ergea.moviekuapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ergea.moviekuapp.data.ApiConfig
import com.ergea.moviekuapp.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @Author: ridhogymnastiar
 * Github: https://github.com/ridhogaa
 */

class MainViewModel() : ViewModel() {

    private val _movie = MutableLiveData<MovieResponse>()
    val movie: LiveData<MovieResponse> = _movie

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        _loadingState.postValue(true)
        delay(3000L)
        val response = ApiConfig.getApiService().getMovies()
        viewModelScope.launch(Dispatchers.Main) {
            _loadingState.postValue(false)
            _movie.postValue(response)
        }
    }

}