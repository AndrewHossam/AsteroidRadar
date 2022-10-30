package com.udacity.asteroidradar.datasource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg asteroid: AsteroidDB)

    @Query("delete from AsteroidDB ")
    suspend fun deleteAsteroid()

    @Query("Select * from AsteroidDB")
    fun getSavedAsteroid(): LiveData<List<AsteroidDB>>

    @Query("Select * from AsteroidDB where date(closeApproachDate) = :date")
    fun getTodayAsteroid(date: String): LiveData<List<AsteroidDB>>

    @Query("Select * from AsteroidDB where date(closeApproachDate) >= :date")
    fun getWeekAsteroid(date: String): LiveData<List<AsteroidDB>>
}