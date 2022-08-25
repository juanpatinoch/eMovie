package com.cinema.emovie.domain.genre

import com.cinema.emovie.data.local.entities.GenreEntity
import com.cinema.emovie.data.repository.genre.GenreRepository
import javax.inject.Inject

class SetGenreLocal @Inject constructor(
    private val genreRepository: GenreRepository
) {

    suspend fun invoke(genreListEntity: List<GenreEntity>) =
        genreRepository.insertGenreLocal(genreListEntity)

}