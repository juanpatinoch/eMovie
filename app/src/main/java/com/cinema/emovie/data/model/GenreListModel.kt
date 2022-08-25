package com.cinema.emovie.data.model

import com.google.gson.annotations.SerializedName

data class GenreListModel(
    @SerializedName("genres")
    val genreList: List<GenreModel>
)