package com.udacity.asteroidradar.models

import android.os.Parcelable
import androidx.room.Entity
import com.udacity.asteroidradar.db.AsteroidEntity
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Asteroid(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean,
) : Parcelable

fun Asteroid.convertToAsteroidEntity(): AsteroidEntity {
    return AsteroidEntity(
        id = this.id,
        codeName = this.codename,
        closeApproachDate = this.closeApproachDate,
        absoluteMagnitude = this.absoluteMagnitude,
        estimatedDiameter = this.estimatedDiameter,
        relativeVelocity = this.relativeVelocity,
        distanceFromEarth = this.distanceFromEarth,
        isPotentiallyHazardous = this.isPotentiallyHazardous,
    )
}

fun List<Asteroid>.convertToAsteroidEntity(): List<AsteroidEntity> {
    return this.map { asteroid ->
        AsteroidEntity(
            id = asteroid.id,
            codeName = asteroid.codename,
            closeApproachDate = asteroid.closeApproachDate,
            absoluteMagnitude = asteroid.absoluteMagnitude,
            estimatedDiameter = asteroid.estimatedDiameter,
            relativeVelocity = asteroid.relativeVelocity,
            distanceFromEarth = asteroid.distanceFromEarth,
            isPotentiallyHazardous = asteroid.isPotentiallyHazardous,
        )
    }
}