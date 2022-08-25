package com.cinema.emovie.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cinema.emovie.data.model.GenreModel

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String?
)

fun List<GenreModel>?.toGenreEntity() = this?.map {
    GenreEntity(
        it.id ?: 0,
        it.name
    )
}