package com.udacity.asteroidradar.datasource.local

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.datasource.remote.dto.AsteroidResponse
import com.udacity.asteroidradar.datasource.remote.dto.PictureOfDayResponse

interface AsteroidRemoteDataSource {
    suspend fun getAsteroids(
        startDate: String,
        endDate: String,
        apiKey: String = BuildConfig.NASA_KEY,
    ): AsteroidResponse

    suspend fun getAsteroidsString(
        startDate: String,
        endDate: String,
        apiKey: String = BuildConfig.NASA_KEY,
    ): String

    suspend fun photoOfTheDay(
        apiKey: String = BuildConfig.NASA_KEY,
    ): PictureOfDayResponse
}