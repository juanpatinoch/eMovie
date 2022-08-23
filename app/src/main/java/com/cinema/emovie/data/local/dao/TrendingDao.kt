package com.cinema.emovie.data.local.dao

import androidx.room.*
import com.cinema.emovie.data.local.entities.TrendingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingDao {

    @Query("SELECT * FROM trending ORDER BY popularity DESC")
    fun getAllTrending(): Flow<List<TrendingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrending(movies: List<TrendingEntity>)

    @Query("DELETE FROM trending")
    suspend fun deleteAllTrending()

    @Transaction
    suspend fun insertAllTrending(movies: List<TrendingEntity>) {
        deleteAllTrending()
        insertTrending(movies)
    }

}