package com.udacity.asteroidradar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.Constants

@Database(
    entities = [AsteroidEntity::class, PictureOfDayEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao: IAsteroidDao

    abstract val pictureOfDayDao: IPictureOfDayDao

    companion object {
        @Volatile
        private var _instance: AsteroidDatabase? = null

        fun initInstanceDatabase(context: Context): AsteroidDatabase = synchronized(this) {
            var instance = _instance
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AsteroidDatabase::class.java,
                    Constants.DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
            _instance = instance
            return instance
        }
    }
}

