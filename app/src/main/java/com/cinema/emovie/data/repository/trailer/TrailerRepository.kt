package com.cinema.emovie.data.repository.trailer

import com.cinema.emovie.data.local.dao.TrailerDao
import com.cinema.emovie.data.local.entities.TrailerEntity
import com.cinema.emovie.data.network.trailer.TrailerDataSource
import javax.inject.Inject

class TrailerRepository @Inject constructor(
    private val trailerDataSource: TrailerDataSource,
    private val trailerDao: TrailerDao
) {

    suspend fun getTrailerDataSource(movieId: Int) = trailerDataSource.getTrailer(movieId)

    suspend fun getTrailerTvDataSource(movieId: Int) = trailerDataSource.getTrailerTv(movieId)

    fun getTrailerLocal(movieId: Int?) = trailerDao.getTrailer(movieId)

    suspend fun insertTrailerLocal(trailerEntityList: List<TrailerEntity>) =
        trailerDao.insertAllTrailer(trailerEntityList)

}