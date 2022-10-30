package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.*
import com.udacity.asteroidradar.datasource.repo.AsteroidRepo
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import kotlinx.coroutines.launch

class MainViewModel(
    private val asteroidRepo: AsteroidRepo,
) : ViewModel(), LifecycleEventObserver {

    private val _loader = MutableLiveData(false)
    val loader: LiveData<Boolean>
        get() = _loader

    private val filter = MutableLiveData(AsteroidFilter.WEEK)

    val asteroidResponseLiveData: LiveData<List<Asteroid>> = Transformations.switchMap(filter) {
        when (it) {
            AsteroidFilter.TODAY -> asteroidRepo.asteroidTodayLiveData
            AsteroidFilter.WEEK -> asteroidRepo.asteroidWeekLiveData
            else -> asteroidRepo.asteroidSavedLiveData
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_CREATE) {
            getAsteroid()
            getPhotoOfTheDay()
        }
    }

    private fun getAsteroid() {
        viewModelScope.launch {
            try {
                _loader.postValue(true)
                asteroidRepo.getAsteroids()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loader.value = false
            }
        }
    }

    val photoOfDayLiveData: LiveData<PictureOfDay?> = asteroidRepo.pictureOfDayMutableLiveData
    private fun getPhotoOfTheDay() {
        viewModelScope.launch {
            try {
                _loader.postValue(true)
                asteroidRepo.getPhotoOfTheDay()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loader.value = false
            }
        }
    }

    fun updateFilter(filter: AsteroidFilter) {
        this.filter.value = filter
    }

    enum class AsteroidFilter(val value: String) {
        SAVED("saved"), TODAY("today"), WEEK("week")
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