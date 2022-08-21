package com.cinema.emovie.domain.get_trending

import com.cinema.emovie.data.repository.get_trending.GetTrendingRepository
import javax.inject.Inject


class GetTrendingAPI @Inject constructor(
    private val getTrendingRepository: GetTrendingRepository
) {

    suspend fun invoke() = getTrendingRepository.getTrendingAPI()

}