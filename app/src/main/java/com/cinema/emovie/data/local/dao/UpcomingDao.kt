package com.cinema.emovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cinema.emovie.data.local.entities.UpcomingEntity

@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming ORDER BY popularity DESC")
    suspend fun getAllUpcoming(): List<UpcomingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUpcoming(movies: List<UpcomingEntity>)

}