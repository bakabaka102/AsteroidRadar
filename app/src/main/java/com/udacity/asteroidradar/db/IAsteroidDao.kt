package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.udacity.asteroidradar.Constants.ASTEROID_TABLE
import com.udacity.asteroidradar.Constants.COLUMN_CLOSE_APPROACH_DATE

@Dao
interface IAsteroidDao {

    @Query("SELECT * FROM $ASTEROID_TABLE ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAllAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM $ASTEROID_TABLE WHERE $COLUMN_CLOSE_APPROACH_DATE = :startDate ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAsteroidsDay(startDate: String): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM $ASTEROID_TABLE WHERE $COLUMN_CLOSE_APPROACH_DATE BETWEEN :startDate AND :endDate ORDER BY $COLUMN_CLOSE_APPROACH_DATE DESC")
    fun getAsteroidsDate(startDate: String, endDate: String): LiveData<List<AsteroidEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg asteroid: AsteroidEntity)

    @Update
    fun update(asteroid: AsteroidEntity)

    @Delete
    fun delete(asteroid: AsteroidEntity)
}
