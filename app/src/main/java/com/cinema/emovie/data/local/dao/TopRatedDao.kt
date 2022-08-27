package com.cinema.emovie.data.local.dao

import androidx.room.*
import com.cinema.emovie.data.local.entities.TopRatedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedDao {

    @Query("SELECT * FROM top_rated ORDER BY vote_average DESC, popularity DESC")
    fun getAllTopRated(): Flow<List<TopRatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRated(movies: List<TopRatedEntity>)

    @Query("DELETE FROM top_rated")
    suspend fun deleteAllTopRated()

    @Transaction
    suspend fun insertAllTopRated(movies: List<TopRatedEntity>) {
        deleteAllTopRated()
        insertTopRated(movies)
    }

}