package com.cinema.emovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinema.emovie.data.local.entities.TrailerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrailerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTrailer(trailerEntityList: List<TrailerEntity>)

    @Query("SELECT * FROM trailer WHERE movie_id = :movieId AND site = 'youtube' ORDER BY official DESC LIMIT 1")
    fun getTrailer(movieId: Int?): Flow<TrailerEntity?>
}