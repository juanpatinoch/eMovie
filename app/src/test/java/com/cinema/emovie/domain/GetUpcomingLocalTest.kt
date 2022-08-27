package com.cinema.emovie.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cinema.emovie.config.MainCoroutineRule
import com.cinema.emovie.config.Mocks.mockUpcomingEntityList
import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import com.cinema.emovie.domain.upcoming.GetUpcomingLocal
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUpcomingLocalTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upcomingRepository: UpcomingRepository

    private lateinit var getUpcomingLocal: GetUpcomingLocal

    @Before
    fun setup() {
        upcomingRepository = mockk()
        getUpcomingLocal = GetUpcomingLocal(upcomingRepository)
    }

    @Test
    fun `when call getUpcomingLocal, should collect mock data`() = coroutineRule.runBlockingTest {
        //Given
        every {
            upcomingRepository.getUpcomingLocal()
        } returns flowOf(mockUpcomingEntityList)

        //When
        val actualData = getUpcomingLocal.invoke()

        //Then
        actualData.collect {
            assertEquals(mockUpcomingEntityList, it)
        }
        verify {
            upcomingRepository.getUpcomingLocal()
        }
    }
}