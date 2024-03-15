package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        return sdf.format(date).also {
            Logger.d("Current day: $it")
        }
    }

    fun getDatePreviousAWeek() : String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val currentTime = System.currentTimeMillis()
        val hourIn7Days = 7 * 24 * 60 * 60 * 1000 // Convert 1 hour to milliseconds
        val afterWeek = Date(currentTime + hourIn7Days)
        return sdf.format(afterWeek).also {
            Logger.d("afterWeek: $it")
        }
    }
}