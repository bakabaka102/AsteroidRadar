package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaAsteroidAPI
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.db.AsteroidEntity
import com.udacity.asteroidradar.db.PictureOfDayEntity
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.models.convertPictureToPictureDB
import com.udacity.asteroidradar.repository.AsteroidRepository
import com.udacity.asteroidradar.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDatabase.initInstanceDatabase(application)
    private val repository = AsteroidRepository(database)

    init {
        /*viewModelScope.launch {
            //loadPictureOfDay()
        }*/
        viewModelScope.launch {
            //loadPictureOfDay()
            repository.loadPictureOfDay()
            //repository.loadAsteroidList()
        }
    }

    private val _pictureOfDay = MutableLiveData<PictureOfDayEntity>()
    val pictureOfDay: LiveData<PictureOfDayEntity> get() = repository.pictureOfDay

    private suspend fun loadPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                _pictureOfDay.postValue(
                    NasaAsteroidAPI.retrofitService.getPictureOfDay(Constants.VALUE_OF_API_KEY).convertPictureToPictureDB().also {
                        Logger.e("_pictureOfDay has value: $it")
                    }
                )
            } catch (err: Exception) {
                Logger.e("_pictureOfDay: ${err.message}")
            }
        }
    }
}