package com.cinema.emovie.domain.trailer

import com.cinema.emovie.data.repository.trailer.TrailerRepository
import javax.inject.Inject

class GetTrailerAPI @Inject constructor(
    private val trailerRepository: TrailerRepository
) {

    suspend fun invoke(movieId: Int) = trailerRepository.getTrailerDataSource(movieId)

}