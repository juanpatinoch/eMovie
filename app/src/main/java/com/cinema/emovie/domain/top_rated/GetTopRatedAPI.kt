package com.cinema.emovie.domain.top_rated

import com.cinema.emovie.data.repository.top_rated.TopRatedRepository
import javax.inject.Inject

class GetTopRatedAPI @Inject constructor(
    private val topRatedRepository: TopRatedRepository
) {

    suspend fun invoke() = topRatedRepository.getTopRatedDataSource()

}