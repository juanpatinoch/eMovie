package com.cinema.emovie.domain.model

import com.cinema.emovie.data.local.entities.TrailerEntity

data class Trailer(
    val id: String,
    val key: String?,
    val site: String?,
    val official: Boolean?
)

fun TrailerEntity.toDomain() = Trailer(this.id, this.key, this.site, this.official)