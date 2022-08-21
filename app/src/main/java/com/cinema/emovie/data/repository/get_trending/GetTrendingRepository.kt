package com.cinema.emovie.data.repository.get_trending

import com.cinema.emovie.data.network.get_trending.GetTrendingDataSource
import javax.inject.Inject

class GetTrendingRepository @Inject constructor(
    private val getTrendingDataSource: GetTrendingDataSource
) {

    suspend fun getTrendingAPI() = getTrendingDataSource.getTrending()

}