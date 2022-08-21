package com.cinema.emovie.ui.home.status

import com.cinema.emovie.domain.model.Movie

sealed class HomeStatus {
    data class SuccessGetUpcoming(val movies: List<Movie>?) : HomeStatus()
    data class Failure(val exception: Exception) : HomeStatus()
}