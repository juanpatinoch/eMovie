package com.cinema.emovie.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cinema.emovie.data.model.TrailerModel

@Entity(tableName = "trailer")
data class TrailerEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "key")
    val key: String?,
    @ColumnInfo(name = "site")
    val site: String?,
    @ColumnInfo(name = "official")
    val official: Boolean?,
    @ColumnInfo(name = "movie_id")
    val movieId: Int?
)

fun List<TrailerModel>?.toTrailerEntity(movieId: Int?) = this?.map {
    TrailerEntity(it.id, it.key, it.site?.lowercase(), it.official, movieId)
}