package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.db.AsteroidDataType
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.db.AsteroidEntity
import com.udacity.asteroidradar.db.PictureOfDayEntity
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidDatabase.initInstanceDatabase(application)
    private val repository = AsteroidRepository(database)

    private var _asteroidDataType = MutableLiveData(AsteroidDataType.ALL_TIME)
    private val asteroidDataType: LiveData<AsteroidDataType> get() = _asteroidDataType

    val pictureOfDay: LiveData<PictureOfDayEntity> = repository.pictureOfDay

    val asteroidList: LiveData<List<AsteroidEntity>> = asteroidDataType.switchMap { type ->
        when (type) {
            AsteroidDataType.TODAY -> repository.todayAsteroidList
            AsteroidDataType.WEEK -> repository.weekAsteroids
            else -> repository.allAsteroidList
        }
    }

    init {
        viewModelScope.launch {
            repository.loadPictureOfDay()
            repository.loadAsteroidList()
        }
    }

    fun onChangeAsteroidDataType(filter: AsteroidDataType) {
        _asteroidDataType.postValue(filter)
    }
}