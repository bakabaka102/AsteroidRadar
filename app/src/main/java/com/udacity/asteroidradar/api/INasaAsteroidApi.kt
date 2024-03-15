package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface INasaAsteroidApi {

    @GET(Constants.ALL_ASTEROIDS)
    suspend fun getAsteroids(
        @Query(Constants.API_KEY)
        apiKey: String
    ): String

    @GET(Constants.PICTURE_OF_DAY)
    suspend fun getPictureOfDay(
        @Query(Constants.API_KEY)
        apiKey: String
    ): String
}

