package com.udacity.asteroidradar.datasource.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.datasource.local.AsteroidDao
import com.udacity.asteroidradar.datasource.remote.AsteroidApi
import com.udacity.asteroidradar.datasource.remote.parseAsteroidsJsonResult
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.domain.mapper.toDatabaseModel
import com.udacity.asteroidradar.domain.mapper.toDomain
import com.udacity.asteroidradar.utils.toFormattedDate
import org.json.JSONObject
import java.util.*

class AsteroidRepo(
    private val asteroidDao: AsteroidDao,
    private val asteroidApi: AsteroidApi,
) {
    val asteroidLiveData: LiveData<List<Asteroid>>
        get() =
            Transformations.map(asteroidDao.getAsteroid()) {
                it.toDomain()
            }

    suspend fun getAsteroids() {
        val remoteResult = asteroidApi.getAsteroidsString(
            Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -7) }
                .toFormattedDate(),
            Calendar.getInstance().toFormattedDate(),
        )
        asteroidDao.deleteAsteroid()
        asteroidDao.insert(*parseAsteroidsJsonResult(JSONObject(remoteResult)).toDatabaseModel()
            .toTypedArray())
    }

    val pictureOfDayMutableLiveData = MutableLiveData<PictureOfDay>()

    suspend fun getPhotoOfTheDay() {
        try {
            val remoteResult = asteroidApi.photoOfTheDay().toDomain()
            pictureOfDayMutableLiveData.value = remoteResult
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
        }
    }

}