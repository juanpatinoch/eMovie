package com.cinema.emovie.domain.top_rated

import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.repository.top_rated.TopRatedRepository
import javax.inject.Inject

class SetTopRatedLocal @Inject constructor(
    private val topRatedRepository: TopRatedRepository
) {

    suspend fun invoke(topRatedEntityList: List<TopRatedEntity>) =
        topRatedRepository.insertTopRatedLocal(topRatedEntityList)

}