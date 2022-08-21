package com.cinema.emovie.domain.get_upcoming

import com.cinema.emovie.data.local.entities.UpcomingEntity
import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import javax.inject.Inject

class SetUpcomingLocal @Inject constructor(
    private val upcomingRepository: UpcomingRepository
) {

    suspend fun invoke(upcomingEntityList: List<UpcomingEntity>) =
        upcomingRepository.insertUpcomingLocal(upcomingEntityList)

}