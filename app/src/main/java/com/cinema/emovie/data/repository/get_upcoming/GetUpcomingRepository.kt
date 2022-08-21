package com.cinema.emovie.data.repository.get_upcoming

import com.cinema.emovie.data.network.get_upcoming.GetUpcomingDataSource
import javax.inject.Inject

class GetUpcomingRepository @Inject constructor(
    private val getUpcomingDataSource: GetUpcomingDataSource
) {

    suspend fun getUpcoming() = getUpcomingDataSource.getUpcoming()

}