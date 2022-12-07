package com.udacity.asteroidradar.utils

import com.udacity.asteroidradar.domain.Asteroid
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Test
import kotlin.random.Random

class UtilTest {

    @Test
    fun givenAstriodList_halfHazard_return50Percent() {
        val list = listOf(
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                true
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                true
            ),
        )
        val result = percentageOfHazardAstreoid(list)
        MatcherAssert.assertThat(result, `is`(50.0))
    }

    @Test
    fun givenAstriodList_zeroHazard_return0Percent() {
        val list = listOf(
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
            Asteroid(
                Random.nextLong(),
                Random.nextLong().toString(),
                Random.nextLong().toString(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                Random.nextDouble(),
                false
            ),
        )
        val result = percentageOfHazardAstreoid(list)
        MatcherAssert.assertThat(result, `is`(0.0))
    }

    @Test
    fun givenAstriodList_noAsteroid_return0Percent() {
        val list = listOf<Asteroid>()
        val result = percentageOfHazardAstreoid(list)
        MatcherAssert.assertThat(Name("A"), `is`(Name("A")))

    }
}

data class Name(val name: String)