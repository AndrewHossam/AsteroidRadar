package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toFormattedDate(): String {
    val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
    return dateFormat.format(time)
}