package com.cinema.emovie.ui.home.status

import com.cinema.emovie.domain.model.Movie

sealed class HomeStatus {
    data class SuccessGetUpcoming(val movies: List<Movie>?) : HomeStatus()
    data class SuccessGetTopRated(val movies: List<Movie>?) : HomeStatus()
    data class Error(val exception: Exception) : HomeStatus()
}