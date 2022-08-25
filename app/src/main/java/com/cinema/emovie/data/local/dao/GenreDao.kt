package com.cinema.emovie.data.local.dao

import androidx.room.*
import com.cinema.emovie.data.local.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre ORDER BY name ASC")
    fun getAllGenres(): Flow<List<GenreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(movies: List<GenreEntity>)

    @Query("DELETE FROM genre")
    suspend fun deleteAllGenres()

    @Transaction
    suspend fun insertAllGenres(genres: List<GenreEntity>) {
        deleteAllGenres()
        insertGenre(genres)
    }

}