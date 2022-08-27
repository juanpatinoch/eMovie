package com.cinema.emovie.domain.trending

import com.cinema.emovie.data.local.entities.TrendingEntity
import com.cinema.emovie.data.repository.trending.TrendingRepository
import javax.inject.Inject

class SetTrendingLocal @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    suspend fun invoke(trendingEntityList: List<TrendingEntity>) =
        trendingRepository.insertTrendingLocal(trendingEntityList)

}