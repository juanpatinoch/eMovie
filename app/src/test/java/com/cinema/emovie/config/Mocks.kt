package com.cinema.emovie.config

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.UpcomingEntity
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.model.MovieModel
import java.util.*
import kotlin.random.Random

object Mocks {
    val mockUpcomingEntityList = listOf(
        UpcomingEntity(
            1,
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            arrayListOf("1", "2"),
            Random.nextDouble(),
            UUID.randomUUID().toString().substring(0, 10),
            UUID.randomUUID().toString().substring(0, 10),
            false,
            Random.nextDouble()
        )
    )

    fun <M> getMockApiResponseFailure() = ApiResponse.Failure<M>(Exception())

    val mockApiResponseSuccessMovieListModel = ApiResponse.Success(
        MovieListModel(
            null, listOf(
                MovieModel(
                    1,
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    arrayListOf(1, 2),
                    Random.nextDouble(),
                    UUID.randomUUID().toString().substring(0, 10),
                    UUID.randomUUID().toString().substring(0, 10),
                    false,
                    Random.nextDouble(),
                    UUID.randomUUID().toString().substring(0, 10)
                )
            ), null, null
        )
    )
}