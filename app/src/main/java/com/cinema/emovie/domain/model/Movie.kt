package com.cinema.emovie.domain.model

import com.cinema.emovie.data.model.MovieListModel

data class Movie(
    val adult: Boolean?,
    val backdropPath: String?,
    val id: Int?,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val mediaType: String?,
    val genreIds: List<Int>?,
    val popularity: Double?,
    val releaseDate: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun MovieListModel.toDomain() = this.movieList?.map {
    Movie(
        it.adult,
        it.backdropPath,
        it.id,
        it.title,
        it.overview,
        it.posterPath,
        it.mediaType,
        it.genreIds,
        it.popularity,
        it.releaseDate,
        it.video,
        it.voteAverage,
        it.voteCount
    )
}