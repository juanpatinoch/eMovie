package com.cinema.emovie.data.local.dao

import androidx.room.*
import com.cinema.emovie.data.local.entities.GenreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT name FROM genre WHERE id IN (:genreIdList)")
    fun getGenreString(genreIdList: List<Int>?): Flow<List<String>>

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