package com.udacity.asteroidradar.datasource.local

import com.udacity.asteroidradar.datasource.remote.AsteroidApi
import com.udacity.asteroidradar.datasource.remote.Network
import com.udacity.asteroidradar.datasource.remote.dto.AsteroidResponse
import com.udacity.asteroidradar.datasource.remote.dto.PictureOfDayResponse

class AsteroidRemoteDataSourceImp(
    private val api: AsteroidApi = Network.asteroidApi.value,
) : AsteroidRemoteDataSource {
    override suspend fun getAsteroids(
        startDate: String,
        endDate: String,
        apiKey: String,
    ): AsteroidResponse =
        api.getAsteroids(startDate, endDate, apiKey)

    override suspend fun getAsteroidsString(
        startDate: String,
        endDate: String,
        apiKey: String,
    ): String =
        api.getAsteroidsString(startDate, endDate, apiKey)

    override suspend fun photoOfTheDay(apiKey: String): PictureOfDayResponse =
        api.photoOfTheDay(apiKey)
}