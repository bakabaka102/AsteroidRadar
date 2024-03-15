package com.udacity.asteroidradar.models

import com.squareup.moshi.Json
import com.udacity.asteroidradar.db.PictureOfDayEntity

data class PictureOfDay(
    @Json(name = "media_type")
    val mediaType: String,
    val explanation: String,
    val title: String,
    val url: String,
)

fun PictureOfDay.convertPictureToPictureDB(): PictureOfDayEntity {
    return PictureOfDayEntity(
        title = this.title,
        url = this.url,
        explanation = this.explanation,
        mediaType = this.mediaType,
    )
}