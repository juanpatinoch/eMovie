package com.cinema.emovie.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cinema.emovie.common.POSTER_IMAGE_URL
import com.cinema.emovie.data.model.MovieModel

@Entity(tableName = "upcoming")
data class UpcomingEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @ColumnInfo(name = "original_name")
    val originalName: String?,
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String?,
    @ColumnInfo(name = "genre_ids")
    val genreIds: ArrayList<String>?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String?,
    @ColumnInfo(name = "video")
    val video: Boolean?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?
)

fun List<MovieModel>?.toUpcomingEntity() = this?.map {
    UpcomingEntity(
        it.id ?: 0,
        it.title,
        it.originalTitle,
        it.originalName,
        it.originalLanguage,
        it.overview,
        "$POSTER_IMAGE_URL${it.posterPath}",
        it.genreIds?.map { num -> num.toString() } as ArrayList<String>,
        it.popularity,
        it.releaseDate,
        it.firstAirDate,
        it.video,
        it.voteAverage
    )
}