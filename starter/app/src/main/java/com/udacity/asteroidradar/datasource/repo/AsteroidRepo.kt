package com.udacity.asteroidradar.datasource.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.datasource.local.AsteroidLocalDataSource
import com.udacity.asteroidradar.datasource.local.AsteroidRemoteDataSource
import com.udacity.asteroidradar.datasource.remote.parseAsteroidsJsonResult
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.domain.mapper.toDatabaseModel
import com.udacity.asteroidradar.domain.mapper.toDomain
import com.udacity.asteroidradar.utils.toFormattedDate
import org.json.JSONObject
import java.util.*

class AsteroidRepo(
    private val asteroidDao: AsteroidLocalDataSource,
    private val asteroidApi: AsteroidRemoteDataSource,
) : IAsteroidRepo {

    private val todayDate = Calendar.getInstance().toFormattedDate()
    private val weekDate =
        Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, -7) }.toFormattedDate()
    override val asteroidSavedLiveData: LiveData<List<Asteroid>>
        get() =
            Transformations.map(asteroidDao.getSavedAsteroid()) {
                it.toDomain()
            }
    override val asteroidTodayLiveData: LiveData<List<Asteroid>>
        get() =
            Transformations.map(asteroidDao.getTodayAsteroid(todayDate)) {
                it.toDomain()
            }
    override val asteroidWeekLiveData: LiveData<List<Asteroid>>
        get() =
            Transformations.map(asteroidDao.getWeekAsteroid(weekDate)) {
                it.toDomain()
            }

    override suspend fun getAsteroids() {

        val remoteResult = asteroidApi.getAsteroidsString(
            Calendar.getInstance().toFormattedDate(),
            Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, +7) }.toFormattedDate(),
        )
        asteroidDao.deleteAsteroid()
        asteroidDao.insert(*parseAsteroidsJsonResult(JSONObject(remoteResult)).toDatabaseModel()
            .toTypedArray())
    }

    override val pictureOfDayMutableLiveData = MutableLiveData<PictureOfDay>()

    override suspend fun getPhotoOfTheDay() {

        try {
            val remoteResult = asteroidApi.photoOfTheDay().toDomain()
            pictureOfDayMutableLiveData.value = remoteResult
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
        }
    }

}