package com.cinema.emovie.domain.trailer

import com.cinema.emovie.data.repository.trailer.TrailerRepository
import javax.inject.Inject

class GetTrailerLocal @Inject constructor(
    private val trailerRepository: TrailerRepository
) {

    fun invoke(movieId: Int?) = trailerRepository.getTrailerLocal(movieId)

}