package com.cinema.emovie.data.repository.upcoming

import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.local.entities.UpcomingEntity
import com.cinema.emovie.data.network.upcoming.UpcomingDataSource
import javax.inject.Inject

class UpcomingRepository @Inject constructor(
    private val upcomingDataSource: UpcomingDataSource,
    private val upcomingDao: UpcomingDao
) {

    suspend fun getUpcomingDataSource() = upcomingDataSource.getUpcoming()

    fun getUpcomingLocal() = upcomingDao.getAllUpcoming()

    suspend fun insertUpcomingLocal(upcomingEntityList: List<UpcomingEntity>) =
        upcomingDao.insertAllUpcoming(upcomingEntityList)

}