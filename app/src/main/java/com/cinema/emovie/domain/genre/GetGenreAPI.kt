package com.cinema.emovie.domain.genre

import com.cinema.emovie.data.repository.genre.GenreRepository
import javax.inject.Inject

class GetGenreAPI @Inject constructor(
    private val genreRepository: GenreRepository
) {

    suspend fun invoke() = genreRepository.getGenreDataSource()

}