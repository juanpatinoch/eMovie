package com.cinema.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cinema.emovie.common.local.Converters
import com.cinema.emovie.data.local.dao.GenreDao
import com.cinema.emovie.data.local.dao.TopRatedDao
import com.cinema.emovie.data.local.dao.TrendingDao
import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.local.entities.GenreEntity
import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.local.entities.TrendingEntity
import com.cinema.emovie.data.local.entities.UpcomingEntity


@Database(
    entities = [
        UpcomingEntity::class,
        TopRatedEntity::class,
        TrendingEntity::class,
        GenreEntity::class
    ], version = 15
)
@TypeConverters(Converters::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getUpcomingDao(): UpcomingDao
    abstract fun getTopRatedDao(): TopRatedDao
    abstract fun getTrendingDao(): TrendingDao
    abstract fun getGenreDao(): GenreDao

}