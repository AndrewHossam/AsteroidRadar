package com.udacity.asteroidradar.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AsteroidDB")
data class AsteroidDB(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean,
)