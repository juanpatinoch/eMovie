package com.cinema.emovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinema.emovie.data.local.entities.TopRatedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedDao {

    @Query("SELECT * FROM top_rated ORDER BY popularity DESC")
    fun getAllTopRated(): Flow<List<TopRatedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRated(movies: List<TopRatedEntity>)

}