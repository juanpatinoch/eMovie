package com.cinema.emovie.domain.get_upcoming

import com.cinema.emovie.data.repository.get_upcoming.GetUpcomingRepository
import javax.inject.Inject

class GetUpcomingAPI @Inject constructor(
    private val getUpcomingRepository: GetUpcomingRepository
) {

    suspend fun invoke() = getUpcomingRepository.getUpcoming()

}