package com.udacity.asteroidradar.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.COLUMN_ABSOLUTE_MAGNITUDE
import com.udacity.asteroidradar.Constants.COLUMN_CLOSE_APPROACH_DATE
import com.udacity.asteroidradar.Constants.COLUMN_CODE_NAME
import com.udacity.asteroidradar.Constants.COLUMN_DISTANCE_FROM_EARTH
import com.udacity.asteroidradar.Constants.COLUMN_ESTIMATE_DIAMETER
import com.udacity.asteroidradar.Constants.COLUMN_ID
import com.udacity.asteroidradar.Constants.COLUMN_IS_POTENTIALLY_HAZARDOUS
import com.udacity.asteroidradar.Constants.COLUMN_RELATIVE_VELOCITY

@Entity(tableName = Constants.ASTEROID_TABLE)
data class AsteroidEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(COLUMN_ID)
    val id: Long,
    @ColumnInfo(COLUMN_CODE_NAME)
    val codeName: String,
    @ColumnInfo(COLUMN_CLOSE_APPROACH_DATE)
    val closeApproachDate: String,
    @ColumnInfo(COLUMN_ABSOLUTE_MAGNITUDE)
    val absoluteMagnitude: Double,
    @ColumnInfo(COLUMN_ESTIMATE_DIAMETER)
    val estimatedDiameter: Double,
    @ColumnInfo(COLUMN_RELATIVE_VELOCITY)
    val relativeVelocity: Double,
    @ColumnInfo(COLUMN_DISTANCE_FROM_EARTH)
    val distanceFromEarth: Double,
    @ColumnInfo(COLUMN_IS_POTENTIALLY_HAZARDOUS)
    val isPotentiallyHazardous: Boolean,
)
