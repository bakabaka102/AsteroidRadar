package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface IAsteroidService {

    @GET(Constants.ALL_ASTEROIDS)
    suspend fun getAsteroids(
        @Query(Constants.API_KEY)
        apiKey: String
    ): List<Asteroid>

    @GET(Constants.PICTURE_OF_DAY)
    suspend fun getPictureOfDay(
        @Query(Constants.API_KEY)
        apiKey: String
    ): PictureOfDay
}

