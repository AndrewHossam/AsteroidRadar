package com.udacity.asteroidradar.datasource.remote

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.datasource.remote.dto.AsteroidResponse
import com.udacity.asteroidradar.datasource.remote.dto.PictureOfDayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApi {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_KEY,
    ): AsteroidResponse

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidsString(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_KEY,
    ): String

    @GET("planetary/apod")
    suspend fun photoOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.NASA_KEY,
    ): PictureOfDayResponse
}