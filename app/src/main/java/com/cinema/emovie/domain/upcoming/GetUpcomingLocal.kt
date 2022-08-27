package com.cinema.emovie.domain.upcoming

import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import javax.inject.Inject

class GetUpcomingLocal @Inject constructor(
    private val upcomingRepository: UpcomingRepository
) {

    fun invoke() = upcomingRepository.getUpcomingLocal()

}