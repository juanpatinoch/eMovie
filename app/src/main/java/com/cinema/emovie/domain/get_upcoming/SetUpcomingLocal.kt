package com.cinema.emovie.domain.get_upcoming

import com.cinema.emovie.data.local.entities.UpcomingEntity
import com.cinema.emovie.data.repository.get_upcoming.GetUpcomingRepository
import javax.inject.Inject

class SetUpcomingLocal @Inject constructor(
    private val upcomingRepository: GetUpcomingRepository
) {

    suspend fun invoke(upcomingEntityList: List<UpcomingEntity>) =
        upcomingRepository.insertUpcomingLocal(upcomingEntityList)

}