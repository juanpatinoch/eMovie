package com.cinema.emovie.domain.model

import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.local.entities.TrendingEntity
import com.cinema.emovie.data.local.entities.UpcomingEntity
import java.io.Serializable

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
) : Serializable

@JvmName("upcomingToDomain")
fun List<UpcomingEntity>.toDomain() = this.map {
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

@JvmName("topRatedToDomain")
fun List<TopRatedEntity>.toDomain() = this.map {
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

@JvmName("trendingToDomain")
fun List<TrendingEntity>.toDomain() = this.map {
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