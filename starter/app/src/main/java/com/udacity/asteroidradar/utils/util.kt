package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.domain.Asteroid

fun percentageOfHazardAstreoid(list: List<Asteroid>): Double {
    if (list.isEmpty())
        return 0.0
    val totalPotentiallyHazardous = list.count { it.isPotentiallyHazardous }
    return totalPotentiallyHazardous.toDouble() / list.size * 100.0
}