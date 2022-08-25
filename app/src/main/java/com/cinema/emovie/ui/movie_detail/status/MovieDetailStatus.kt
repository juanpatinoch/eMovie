package com.cinema.emovie.ui.movie_detail.status

import com.cinema.emovie.domain.model.Trailer

sealed class MovieDetailStatus {
    data class SuccessGetGenre(val genres: String?) : MovieDetailStatus()
    data class SuccessGetTrailer(val trailer: Trailer?) : MovieDetailStatus()
    data class Error(val exception: Exception) : MovieDetailStatus()
}
