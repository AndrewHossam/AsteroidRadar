package com.udacity.asteroidradar

import android.app.Application
import androidx.work.*
import com.udacity.asteroidradar.worker.AsteroidWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class App : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        startWorker()
    }

    private fun startWorker() {
        applicationScope.launch {
            val workRequest = PeriodicWorkRequestBuilder<AsteroidWorker>(1, TimeUnit.DAYS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresCharging(true)
                        .build()

                )
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                AsteroidWorker::class.java.simpleName,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}