package com.cinema.emovie.data.repository.trending

import com.cinema.emovie.data.network.trending.TrendingDataSource
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingDataSource: TrendingDataSource
) {

    suspend fun getTrendingDataSource(
        mediaType: String,
        timeWindow: String
    ) = trendingDataSource.getTrending(mediaType, timeWindow)

}