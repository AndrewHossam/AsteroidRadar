package com.udacity.asteroidradar.domain.mapper

import com.udacity.asteroidradar.datasource.remote.dto.PictureOfDayResponse
import com.udacity.asteroidradar.domain.PictureOfDay

fun PictureOfDayResponse.toDomain() = PictureOfDay(mediaType, title, url)