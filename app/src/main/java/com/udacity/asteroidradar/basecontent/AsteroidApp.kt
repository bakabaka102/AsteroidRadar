package com.udacity.asteroidradar.basecontent

import android.app.Application
import android.os.Build
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.udacity.asteroidradar.schedulerworker.DataWorkerRefresh
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidApp : Application() {

    private val mScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        setUpWork()
    }

    private fun setUpWork() {
        mScope.launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    setRequiresDeviceIdle(true)
                }.build()

            val repeatingRequest = PeriodicWorkRequestBuilder<DataWorkerRefresh>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                DataWorkerRefresh.WORKER_REFRESH_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        }
    }
}