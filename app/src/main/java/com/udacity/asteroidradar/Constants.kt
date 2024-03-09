package com.udacity.asteroidradar

object Constants {

    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7

    //Room DB
    const val DATABASE_NAME = "asteroid_db"
    const val ASTEROID_TABLE = "asteroids"
    const val COLUMN_ID = "id"
    const val COLUMN_CODE_NAME = "code_name"
    const val COLUMN_CLOSE_APPROACH_DATE = "close_approach_date"
    const val COLUMN_ABSOLUTE_MAGNITUDE = "absolute_magnitude"
    const val COLUMN_ESTIMATE_DIAMETER = "estimated_diameter"
    const val COLUMN_RELATIVE_VELOCITY = "relative_velocity"
    const val COLUMN_DISTANCE_FROM_EARTH = "distance_from_earth"
    const val COLUMN_IS_POTENTIALLY_HAZARDOUS = "is_potentially_hazardous"

    const val PICTURE_OF_DAY_TABLE = "picture_of_day"

    //Retrofit
    const val BASE_URL = "https://api.nasa.gov/"
    const val VALUE_OF_API_KEY = "Wm9fMUOloYCkeBxLuGBRL9lCyU1sAXrZeFNHjdeZ"
    const val API_KEY = "api_key"
    const val ALL_ASTEROIDS = "neo/rest/v1/feed"
    const val PICTURE_OF_DAY = "planetary/apod"

}