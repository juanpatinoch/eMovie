package com.cinema.emovie.data.di

import android.content.Context
import androidx.room.Room
import com.cinema.emovie.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun roomProvider(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            MoviesDatabase::class.java,
            "emovie_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun upcomingDaoProvider(db: MoviesDatabase) = db.getUpcomingDao()

}