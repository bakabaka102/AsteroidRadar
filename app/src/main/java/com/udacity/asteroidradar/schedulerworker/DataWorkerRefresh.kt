package com.udacity.asteroidradar.schedulerworker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.db.AsteroidDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import retrofit2.HttpException

class DataWorkerRefresh(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    companion object {
        const val WORKER_REFRESH_NAME = "worker_refresh_name"
    }

    override suspend fun doWork(): Result {
        val database = AsteroidDatabase.initInstanceDatabase(appContext.applicationContext)
        val repository = AsteroidRepository(database)
        return try {
            repository.loadAsteroidList()
            repository.loadPictureOfDay()
            Result.success()
        } catch (ex: HttpException) {
            Result.retry()
        }
    }
}