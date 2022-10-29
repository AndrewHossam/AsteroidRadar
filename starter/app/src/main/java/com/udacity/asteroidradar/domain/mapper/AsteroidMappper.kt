package com.udacity.asteroidradar.domain.mapper

import com.udacity.asteroidradar.datasource.local.AsteroidDB
import com.udacity.asteroidradar.domain.Asteroid

fun List<AsteroidDB>.toDomain() =
    map {
        Asteroid(
            it.id,
            it.codename,
            it.closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameter,
            it.relativeVelocity,
            it.distanceFromEarth,
            it.isPotentiallyHazardous
        )
    }

fun ArrayList<Asteroid>.toDatabaseModel() =
    map {
        AsteroidDB(
            it.id,
            it.codename,
            it.closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameter,
            it.relativeVelocity,
            it.distanceFromEarth,
            it.isPotentiallyHazardous
        )
    }