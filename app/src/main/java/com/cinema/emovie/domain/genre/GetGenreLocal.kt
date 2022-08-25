package com.cinema.emovie.domain.genre

import com.cinema.emovie.data.repository.genre.GenreRepository
import javax.inject.Inject

class GetGenreLocal @Inject constructor(
    private val genreRepository: GenreRepository
) {

    fun invoke(genreIdList: List<Int>?) = genreRepository.getGenreLocal(genreIdList)

}