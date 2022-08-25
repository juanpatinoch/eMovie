package com.cinema.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cinema.emovie.common.local.Converters
import com.cinema.emovie.data.local.dao.*
import com.cinema.emovie.data.local.entities.*


@Database(
    entities = [
        UpcomingEntity::class,
        TopRatedEntity::class,
        TrendingEntity::class,
        GenreEntity::class,
        TrailerEntity::class
    ], version = 18
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getUpcomingDao(): UpcomingDao
    abstract fun getTopRatedDao(): TopRatedDao
    abstract fun getTrendingDao(): TrendingDao
    abstract fun getGenreDao(): GenreDao
    abstract fun getTrailerDao(): TrailerDao

}