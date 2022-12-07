package com.udacity.asteroidradar.datasource.local

import androidx.lifecycle.LiveData

interface AsteroidLocalDataSource {
     suspend fun insert(vararg asteroid: AsteroidDB)

     suspend fun deleteAsteroid()

     fun getSavedAsteroid(): LiveData<List<AsteroidDB>>

     fun getTodayAsteroid(date: String): LiveData<List<AsteroidDB>>

     fun getWeekAsteroid(date: String): LiveData<List<AsteroidDB>>
}