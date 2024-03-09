package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.api.NasaAsteroidAPI
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.db.PictureOfDayEntity
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.models.convertPictureToPictureDB
import com.udacity.asteroidradar.models.convertToAsteroidEntity
import com.udacity.asteroidradar.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.ArrayList

class AsteroidRepository(private val database: AsteroidDatabase) {

    /*    val asteroidList: LiveData<List<AsteroidEntity>> get() = database.asteroidDao.getAllAsteroids()

        val todayAsteroidList: LiveData<List<AsteroidEntity>>
            get() = database.asteroidDao.getAsteroidsDay(DateUtils.getCurrentDate())*/

    val pictureOfDay: LiveData<PictureOfDayEntity> get() = database.pictureOfDayDao.getPictureOfDay()

    suspend fun loadPictureOfDay() {
        withContext(Dispatchers.IO) {
            try {
                val responseData =
                    NasaAsteroidAPI.retrofitService.getPictureOfDay(Constants.VALUE_OF_API_KEY)
                        .also {
                            Logger.d("_pictureOfDay has value: $it")
                        }
                val picture : PictureOfDay =
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                        .adapter(PictureOfDay::class.java).fromJson(responseData)
                        ?: PictureOfDay(
                            Constants.EMPTY_STRING,
                            Constants.EMPTY_STRING,
                            Constants.EMPTY_STRING,
                            Constants.EMPTY_STRING,
                        )
                database.pictureOfDayDao.updateData(picture.convertPictureToPictureDB())

            } catch (err: Exception) {
                Logger.e("_pictureOfDay: ${err.message}")
            }
        }
    }

    suspend fun loadAsteroidList() {
        withContext(Dispatchers.IO) {
            try {
                val responseData: String =
                    NasaAsteroidAPI.retrofitService.getAsteroids(Constants.VALUE_OF_API_KEY)
                val json = JSONObject(responseData)
                val asteroids: ArrayList<Asteroid> = parseAsteroidsJsonResult(json)
                asteroids.convertToAsteroidEntity().let {
                    database.asteroidDao.updateData(it)
                    Logger.d("pictureOfDay_asteroids data converted: ===== $it")
                }
                Logger.d("pictureOfDay_asteroids data $asteroids")

            } catch (e: Exception) {
                Logger.e("pictureOfDay_asteroids ---- ${e.message}")
            }
        }
    }

}