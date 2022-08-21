package com.cinema.emovie.domain.model

import com.cinema.emovie.data.local.entities.MovieEntity

data class Movie(
    val adult: Boolean?,
    val backdropUrl: String?,
    val id: Int?,
    val title: String?,
    val overview: String?,
    val posterUrl: String?,
    val mediaType: String?,
    val genreIds: List<Int>?,
    val popularity: Double?,
    val releaseDate: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)

fun List<MovieEntity>.toDomain() = this.map {
    Movie(
        it.adult,
        it.backdropUrl,
        it.id,
        it.title,
        it.overview,
        it.posterUrl,
        it.mediaType,
        it.genreIds?.map { num -> num.toInt() } as ArrayList<Int>,
        it.popularity,
        it.releaseDate,
        it.video,
        it.voteAverage,
        it.voteCount
    )
}