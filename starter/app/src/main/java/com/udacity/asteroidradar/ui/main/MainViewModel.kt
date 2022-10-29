package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.datasource.repo.AsteroidRepo
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.launch

class MainViewModel(
    private val asteroidRepo: AsteroidRepo,
) : ViewModel() {

    init {
        getAsteroid()
        getPhotoOfTheDay()
    }

    private val _loader = MutableLiveData(false)
    val loader: LiveData<Boolean>
        get() = _loader

    val asteroidResponseLiveData: LiveData<List<Asteroid>> = asteroidRepo.asteroidLiveData

    private fun getAsteroid() {
        viewModelScope.launch {
            asteroidRepo.getAsteroids()
        }
    }

    val photoOfDayLiveData: LiveData<PictureOfDay?> = asteroidRepo.pictureOfDayMutableLiveData
    private fun getPhotoOfTheDay() {
        viewModelScope.launch {
            asteroidRepo.getPhotoOfTheDay()
        }
    }

    companion object {
        class ViewModelFactory(private val asteroidRepo: AsteroidRepo) : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    MainViewModel(asteroidRepo) as T
                } else {
                    throw IllegalArgumentException("Unable to construct viewmodel")
                }
            }
        }
    }
}