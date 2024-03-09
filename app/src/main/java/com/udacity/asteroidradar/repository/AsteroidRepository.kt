package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaAsteroidAPI
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.db.AsteroidEntity
import com.udacity.asteroidradar.db.PictureOfDayEntity
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.models.convertPictureToPictureDB
import com.udacity.asteroidradar.utils.DateUtils
import com.udacity.asteroidradar.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AsteroidRepository(private val database: AsteroidDatabase) {

    /*    val asteroidList: LiveData<List<AsteroidEntity>> get() = database.asteroidDao.getAllAsteroids()

        val todayAsteroidList: LiveData<List<AsteroidEntity>>
            get() = database.asteroidDao.getAsteroidsDay(DateUtils.getCurrentDate())*/

    val pictureOfDay: LiveData<PictureOfDayEntity>
        get() = database.pictureOfDayDao.getPictureOfDay().also {
            Logger.d("_pictureOfDay from db has: $it")
            Logger.d("_pictureOfDay from db has value: ${it.value}")
        }


    suspend fun loadPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                val pictureOfDay: PictureOfDay =
                    NasaAsteroidAPI.retrofitService.getPictureOfDay(Constants.VALUE_OF_API_KEY)
                        .also {
                            Logger.d("_pictureOfDay has value: $it")
                        }

                database.pictureOfDayDao.updateData(pictureOfDay.convertPictureToPictureDB())

            } catch (err: Exception) {
                Logger.e("_pictureOfDay: ${err.message}")
            }
        }
    }

    suspend fun loadAsteroidList() {
        withContext(Dispatchers.IO) {
            try {
                val asteroids: List<AsteroidEntity>? =
                    NasaAsteroidAPI.retrofitService.getAsteroids(Constants.VALUE_OF_API_KEY)
                asteroids?.let { database.asteroidDao.updateData(it) }
                Logger.d("data $asteroids")

            } catch (e: Exception) {
                Logger.e("asteroids ---- ${e.message}")
            }
        }
    }

}