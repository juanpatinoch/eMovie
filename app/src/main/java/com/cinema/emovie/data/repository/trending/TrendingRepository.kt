package com.cinema.emovie.data.repository.trending

import com.cinema.emovie.data.local.dao.TrendingDao
import com.cinema.emovie.data.local.entities.TrendingEntity
import com.cinema.emovie.data.network.trending.TrendingDataSource
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingDataSource: TrendingDataSource,
    private val trendingDao: TrendingDao
) {

    suspend fun getTrendingDataSource(
        mediaType: String,
        timeWindow: String
    ) = trendingDataSource.getTrending(mediaType, timeWindow)

    fun getTrendingLocal() = trendingDao.getAllTrending()

    suspend fun insertTrendingLocal(trendingEntityList: List<TrendingEntity>) =
        trendingDao.insertAllTrending(trendingEntityList)

}