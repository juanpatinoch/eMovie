package com.cinema.emovie.domain.model

import com.cinema.emovie.common.MEDIA_TYPE_DEFAULT
import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.local.entities.TrendingEntity
import com.cinema.emovie.data.local.entities.UpcomingEntity
import java.io.Serializable

data class Movie(
    val id: Int?,
    val title: String?,
    val originalTitle: String?,
    val originalName: String?,
    val originalLanguage: String?,
    val overview: String?,
    val posterUrl: String?,
    val genreIds: List<Int>?,
    val popularity: Double?,
    val releaseDate: String?,
    val firstAirDate: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val mediaType: String?
) : Serializable

@JvmName("upcomingToDomain")
fun List<UpcomingEntity>.toDomain() = this.map {
    Movie(
        it.id,
        it.title,
        it.originalTitle,
        it.originalName,
        it.originalLanguage,
        it.overview,
        it.posterUrl,
        it.genreIds?.map { num -> num.toInt() } as List<Int>,
        it.popularity,
        it.releaseDate,
        it.firstAirDate,
        it.video,
        it.voteAverage,
        MEDIA_TYPE_DEFAULT
    )
}

@JvmName("topRatedToDomain")
fun List<TopRatedEntity>.toDomain() = this.map {
    Movie(
        it.id,
        it.title,
        it.originalTitle,
        it.originalName,
        it.originalLanguage,
        it.overview,
        it.posterUrl,
        it.genreIds?.map { num -> num.toInt() } as List<Int>,
        it.popularity,
        it.releaseDate,
        it.firstAirDate,
        it.video,
        it.voteAverage,
        MEDIA_TYPE_DEFAULT
    )
}

@JvmName("trendingToDomain")
fun List<TrendingEntity>.toDomain() = this.map {
    Movie(
        it.id,
        it.title,
        it.originalTitle,
        it.originalName,
        it.originalLanguage,
        it.overview,
        it.posterUrl,
        it.genreIds?.map { num -> num.toInt() } as List<Int>,
        it.popularity,
        it.releaseDate,
        it.firstAirDate,
        it.video,
        it.voteAverage,
        it.mediaType
    )
}