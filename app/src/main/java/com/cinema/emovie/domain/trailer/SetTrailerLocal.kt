package com.cinema.emovie.domain.trailer

import com.cinema.emovie.data.local.entities.TrailerEntity
import com.cinema.emovie.data.repository.trailer.TrailerRepository
import javax.inject.Inject

class SetTrailerLocal @Inject constructor(
    private val trailerRepository: TrailerRepository
) {

    suspend fun invoke(trailerEntityList: List<TrailerEntity>) =
        trailerRepository.insertTrailerLocal(trailerEntityList)

}