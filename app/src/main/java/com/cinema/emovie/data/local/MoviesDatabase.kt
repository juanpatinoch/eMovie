package com.cinema.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cinema.emovie.common.local.Converters
import com.cinema.emovie.data.local.dao.TopRatedDao
import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.local.entities.UpcomingEntity


@Database(
    entities = [
        UpcomingEntity::class,
        TopRatedEntity::class
    ], version = 3
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getUpcomingDao(): UpcomingDao
    abstract fun getTopRatedDao(): TopRatedDao

}