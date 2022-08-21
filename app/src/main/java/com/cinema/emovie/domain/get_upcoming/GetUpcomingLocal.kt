package com.cinema.emovie.domain.get_upcoming

import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import javax.inject.Inject

class GetUpcomingLocal @Inject constructor(
    private val upcomingRepository: UpcomingRepository
) {

    fun invoke() = upcomingRepository.getUpcomingLocal()

}