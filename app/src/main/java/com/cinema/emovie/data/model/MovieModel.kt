package com.cinema.emovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("video")
    val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?
)