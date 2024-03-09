package com.udacity.asteroidradar.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.Constants

@Entity(tableName = Constants.PICTURE_OF_DAY_TABLE)
data class PictureOfDayEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Long? = null,
    @ColumnInfo(name = "media_type")
    val mediaType: String,
    val explanation: String,
    val title: String,
    val url: String,
) {
    var timeStamp: Long = 0
}