package com.cinema.emovie.data.repository.get_upcoming

import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.local.entities.UpcomingEntity
import com.cinema.emovie.data.network.get_upcoming.GetUpcomingDataSource
import javax.inject.Inject

class GetUpcomingRepository @Inject constructor(
    private val getUpcomingDataSource: GetUpcomingDataSource,
    private val upcomingDao: UpcomingDao
) {

    suspend fun getUpcoming() = getUpcomingDataSource.getUpcoming()

    suspend fun insertUpcomingLocal(upcomingEntityList: List<UpcomingEntity>) =
        upcomingDao.insertAllUpcoming(upcomingEntityList)

}