package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidAPI
import com.udacity.asteroidradar.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    init {
        viewModelScope.launch {
            loadPictureOfDay()
        }
    }

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay> get() = _pictureOfDay

    private suspend fun loadPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                _pictureOfDay.postValue(
                    AsteroidAPI.retrofitService.getPictureOfDay(Constants.VALUE_OF_API_KEY).also {
                        Logger.e("_pictureOfDay has value: $it")
                    }
                )
            } catch (err: Exception) {
                Logger.e("_pictureOfDay: ${err.printStackTrace()}")
            }
        }
    }
}