package com.udacity.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.datasource.repo.AsteroidRepo

class AsteroidWorker(private val repo: AsteroidRepo, context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            repo.getAsteroids()
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }

    }
}