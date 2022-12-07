package com.udacity.asteroidradar.datasource.local

import androidx.lifecycle.LiveData

class AsteroidLocalDataSourceImp(
    private val asteroidDao: AsteroidDao,
) : AsteroidLocalDataSource {
    override suspend fun insert(vararg asteroid: AsteroidDB) = asteroidDao.insert(*asteroid)

    override suspend fun deleteAsteroid() = asteroidDao.deleteAsteroid()

    override fun getSavedAsteroid(): LiveData<List<AsteroidDB>> = asteroidDao.getSavedAsteroid()

    override fun getTodayAsteroid(date: String): LiveData<List<AsteroidDB>> =
        asteroidDao.getTodayAsteroid(date)

    override fun getWeekAsteroid(date: String): LiveData<List<AsteroidDB>> =
        asteroidDao.getWeekAsteroid(date)
}