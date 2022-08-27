package com.cinema.emovie.data.model

import com.google.gson.annotations.SerializedName

data class TrailerModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("key")
    val key: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("official")
    val official: Boolean?
)