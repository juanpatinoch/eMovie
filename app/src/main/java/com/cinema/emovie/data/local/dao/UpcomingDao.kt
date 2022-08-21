package com.cinema.emovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinema.emovie.data.local.entities.UpcomingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming ORDER BY popularity DESC")
    fun getAllUpcoming(): Flow<List<UpcomingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcoming(movies: List<UpcomingEntity>)

}