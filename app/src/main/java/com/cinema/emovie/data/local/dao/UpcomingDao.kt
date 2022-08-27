package com.cinema.emovie.data.local.dao

import androidx.room.*
import com.cinema.emovie.data.local.entities.UpcomingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming ORDER BY vote_average DESC, popularity DESC")
    fun getAllUpcoming(): Flow<List<UpcomingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcoming(movies: List<UpcomingEntity>)

    @Query("DELETE FROM upcoming")
    suspend fun deleteAllUpcoming()

    @Transaction
    suspend fun insertAllUpcoming(movies: List<UpcomingEntity>) {
        deleteAllUpcoming()
        insertUpcoming(movies)
    }

}