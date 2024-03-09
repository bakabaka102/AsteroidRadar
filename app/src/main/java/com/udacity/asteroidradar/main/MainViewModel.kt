package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.db.PictureOfDayEntity
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDatabase.initInstanceDatabase(application)
    private val repository = AsteroidRepository(database)

    init {
        viewModelScope.launch {
            //loadPictureOfDay()
            repository.loadPictureOfDay()
            //repository.loadAsteroidList()
        }
    }

    val pictureOfDay: LiveData<PictureOfDayEntity> = repository.pictureOfDay
}