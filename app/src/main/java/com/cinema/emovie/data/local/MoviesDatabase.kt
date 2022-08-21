package com.cinema.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cinema.emovie.common.local.Converters
import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.local.entities.MovieEntity


@Database(
    entities = [
        MovieEntity::class
    ], version = 2
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getUpcomingDao(): UpcomingDao

}