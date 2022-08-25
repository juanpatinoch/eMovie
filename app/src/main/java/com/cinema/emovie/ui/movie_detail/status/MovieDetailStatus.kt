package com.cinema.emovie.ui.movie_detail.status

sealed class MovieDetailStatus {
    data class SuccessGetGenre(val genres: String?) : MovieDetailStatus()
    data class Error(val exception: Exception) : MovieDetailStatus()
}
