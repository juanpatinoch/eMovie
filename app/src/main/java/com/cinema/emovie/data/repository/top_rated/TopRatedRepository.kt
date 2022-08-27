package com.cinema.emovie.data.repository.top_rated

import com.cinema.emovie.data.local.dao.TopRatedDao
import com.cinema.emovie.data.local.entities.TopRatedEntity
import com.cinema.emovie.data.network.top_rated.TopRatedDataSource
import javax.inject.Inject

class TopRatedRepository @Inject constructor(
    private val topRatedDataSource: TopRatedDataSource,
    private val topRatedDao: TopRatedDao
) {

    suspend fun getTopRatedDataSource() = topRatedDataSource.getTopRated()

    fun getTopRatedLocal() = topRatedDao.getAllTopRated()

    suspend fun insertTopRatedLocal(topRatedEntityList: List<TopRatedEntity>) =
        topRatedDao.insertAllTopRated(topRatedEntityList)

}