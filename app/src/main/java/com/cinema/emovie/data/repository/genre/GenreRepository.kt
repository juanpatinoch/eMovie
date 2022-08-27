package com.cinema.emovie.data.repository.genre

import com.cinema.emovie.data.local.dao.GenreDao
import com.cinema.emovie.data.local.entities.GenreEntity
import com.cinema.emovie.data.network.genre.GenreDataSource
import javax.inject.Inject

class GenreRepository @Inject constructor(
    private val genreDataSource: GenreDataSource,
    private val genreDao: GenreDao
) {

    suspend fun getGenreDataSource() = genreDataSource.getGenre()

    fun getGenreLocal(genreIdList: List<Int>?) = genreDao.getGenreString(genreIdList)

    suspend fun insertGenreLocal(genreEntityList: List<GenreEntity>) =
        genreDao.insertAllGenres(genreEntityList)

}