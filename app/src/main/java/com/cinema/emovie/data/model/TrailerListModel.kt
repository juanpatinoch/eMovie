package com.cinema.emovie.data.model

import com.google.gson.annotations.SerializedName

data class TrailerListModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("results")
    val trailerModelList: List<TrailerModel>?
)