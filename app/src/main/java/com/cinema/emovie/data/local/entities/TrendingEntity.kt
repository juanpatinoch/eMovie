package com.cinema.emovie.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cinema.emovie.common.POSTER_IMAGE_URL
import com.cinema.emovie.data.model.MovieModel

@Entity(tableName = "trending")
data class TrendingEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "adult")
    val adult: Boolean?,
    @ColumnInfo(name = "backdrop_url")
    val backdropUrl: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String?,
    @ColumnInfo(name = "media_type")
    val mediaType: String?,
    @ColumnInfo(name = "genre_ids")
    val genreIds: ArrayList<String>?,
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @ColumnInfo(name = "video")
    val video: Boolean?,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?
)

fun List<MovieModel>?.toTrendingEntity() = this?.map {
    TrendingEntity(
        it.id ?: 0,
        it.adult,
        "$POSTER_IMAGE_URL${it.backdropPath}",
        it.title,
        it.overview,
        "$POSTER_IMAGE_URL${it.posterPath}",
        it.mediaType,
        it.genreIds?.map { num -> num.toString() } as ArrayList<String>,
        it.popularity,
        it.releaseDate,
        it.video,
        it.voteAverage,
        it.voteCount
    )
}