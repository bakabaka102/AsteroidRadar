package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.udacity.asteroidradar.Constants.ASTEROID_TABLE
import com.udacity.asteroidradar.Constants.COLUMN_CLOSE_APPROACH_DATE

@Dao
interface IAsteroidDao {

    @Query("SELECT * FROM $ASTEROID_TABLE ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAllAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM $ASTEROID_TABLE WHERE $COLUMN_CLOSE_APPROACH_DATE = :closeApproachDate ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAsteroidsDay(closeApproachDate: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM $ASTEROID_TABLE WHERE $COLUMN_CLOSE_APPROACH_DATE BETWEEN :startDate AND :endDate ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAsteroidsGapDate(startDate: String, endDate: String): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg asteroid: AsteroidEntity)

    @Transaction
    fun updateData(asteroids: List<AsteroidEntity>): List<Long> {
        deleteAll()
        return insertAll(asteroids)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidEntity>): List<Long>

    @Query("DELETE FROM $ASTEROID_TABLE")
    fun deleteAll()
}
