package com.udacity.asteroidradar.api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.db.PictureOfDayEntity

@Dao
interface IPictureOfDayDao {

    @Query("SELECT * FROM ${Constants.PICTURE_OF_DAY_TABLE}")
    fun getPictureOfDay(): LiveData<PictureOfDayEntity>

    @Query("SELECT * FROM ${Constants.PICTURE_OF_DAY_TABLE}")
    fun pictureOfDay(): PictureOfDayEntity

    @Transaction
    fun updateData(picture: PictureOfDayEntity?): Long {
        deleteAll()
        return picture?.let { insert(it) } ?: -1L
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(picture: PictureOfDayEntity): Long

    @Query("DELETE FROM ${Constants.PICTURE_OF_DAY_TABLE}")
    fun deleteAll()
}