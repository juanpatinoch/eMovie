package com.cinema.emovie.domain.trending

import com.cinema.emovie.data.repository.trending.TrendingRepository
import javax.inject.Inject

class GetTrendingAPI @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    suspend fun invoke(
        mediaType: String,
        timeWindow: String
    ) = trendingRepository.getTrendingDataSource(mediaType, timeWindow)

}