package com.udacity.asteroidradar.datasource.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AsteroidResponse(
    @Json(name = "element_count")
    val elementCount: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "near_earth_objects")
    val nearEarthObjects: Map<String, List<AsteroidDetails>>,
) {
    @JsonClass(generateAdapter = true)
    data class Links(
        @Json(name = "next")
        val next: String,
        @Json(name = "previous")
        val previous: String,
        @Json(name = "self")
        val self: String,
    )

    @JsonClass(generateAdapter = true)
    data class AsteroidDetails(
        @Json(name = "absolute_magnitude_h")
        val absoluteMagnitudeH: Double,
        @Json(name = "close_approach_data")
        val closeApproachData: List<CloseApproachData>,
        @Json(name = "estimated_diameter")
        val estimatedDiameter: EstimatedDiameter,
        @Json(name = "id")
        val id: String,
        @Json(name = "is_potentially_hazardous_asteroid")
        val isPotentiallyHazardousAsteroid: Boolean,
        @Json(name = "is_sentry_object")
        val isSentryObject: Boolean,
        @Json(name = "links")
        val links: Links,
        @Json(name = "name")
        val name: String,
        @Json(name = "nasa_jpl_url")
        val nasaJplUrl: String,
        @Json(name = "neo_reference_id")
        val neoReferenceId: String,
    ) {
        @JsonClass(generateAdapter = true)
        data class CloseApproachData(
            @Json(name = "close_approach_date")
            val closeApproachDate: String,
            @Json(name = "close_approach_date_full")
            val closeApproachDateFull: String,
            @Json(name = "epoch_date_close_approach")
            val epochDateCloseApproach: Long,
            @Json(name = "miss_distance")
            val missDistance: MissDistance,
            @Json(name = "orbiting_body")
            val orbitingBody: String,
            @Json(name = "relative_velocity")
            val relativeVelocity: RelativeVelocity,
        ) {
            @JsonClass(generateAdapter = true)
            data class MissDistance(
                @Json(name = "astronomical")
                val astronomical: String,
                @Json(name = "kilometers")
                val kilometers: String,
                @Json(name = "lunar")
                val lunar: String,
                @Json(name = "miles")
                val miles: String,
            )

            @JsonClass(generateAdapter = true)
            data class RelativeVelocity(
                @Json(name = "kilometers_per_hour")
                val kilometersPerHour: String,
                @Json(name = "kilometers_per_second")
                val kilometersPerSecond: String,
                @Json(name = "miles_per_hour")
                val milesPerHour: String,
            )
        }

        @JsonClass(generateAdapter = true)
        data class EstimatedDiameter(
            @Json(name = "feet")
            val feet: Feet,
            @Json(name = "kilometers")
            val kilometers: Kilometers,
            @Json(name = "meters")
            val meters: Meters,
            @Json(name = "miles")
            val miles: Miles,
        ) {
            @JsonClass(generateAdapter = true)
            data class Feet(
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double,
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double,
            )

            @JsonClass(generateAdapter = true)
            data class Kilometers(
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double,
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double,
            )

            @JsonClass(generateAdapter = true)
            data class Meters(
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double,
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double,
            )

            @JsonClass(generateAdapter = true)
            data class Miles(
                @Json(name = "estimated_diameter_max")
                val estimatedDiameterMax: Double,
                @Json(name = "estimated_diameter_min")
                val estimatedDiameterMin: Double,
            )
        }

        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "self")
            val self: String,
        )
    }
}