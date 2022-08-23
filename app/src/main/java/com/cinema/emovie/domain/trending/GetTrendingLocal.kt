package com.cinema.emovie.domain.trending

import com.cinema.emovie.data.repository.trending.TrendingRepository
import javax.inject.Inject

class GetTrendingLocal @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    fun invoke() = trendingRepository.getTrendingLocal()

}