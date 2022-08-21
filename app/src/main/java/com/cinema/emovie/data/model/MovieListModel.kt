package com.cinema.emovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieListModel(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movieList: List<MovieModel>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)