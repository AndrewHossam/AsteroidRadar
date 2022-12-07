package com.udacity.asteroidradar.datasource.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay

interface IAsteroidRepo {
    val asteroidSavedLiveData: LiveData<List<Asteroid>>
    val asteroidTodayLiveData: LiveData<List<Asteroid>>
    val asteroidWeekLiveData: LiveData<List<Asteroid>>

    val pictureOfDayMutableLiveData: MutableLiveData<PictureOfDay>
    suspend fun getAsteroids()

    suspend fun getPhotoOfTheDay()
}