package com.cinema.emovie.domain.model

import com.cinema.emovie.data.local.entities.GenreEntity

data class Genre(
    val id: Int?,
    val name: String?
)

fun List<GenreEntity>.toDomain() = this.map {
    Genre(
        it.id,
        it.name
    )
}