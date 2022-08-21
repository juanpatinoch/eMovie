package com.cinema.emovie.domain.upcoming

import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import javax.inject.Inject

class GetUpcomingAPI @Inject constructor(
    private val upcomingRepository: UpcomingRepository
) {

    suspend fun invoke() = upcomingRepository.getUpcomingDataSource()

}