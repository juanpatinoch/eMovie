package com.cinema.emovie.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cinema.emovie.config.MainCoroutineRule
import com.cinema.emovie.config.Mocks.getMockApiResponseFailure
import com.cinema.emovie.config.Mocks.mockApiResponseSuccessMovieListModel
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import com.cinema.emovie.domain.upcoming.GetUpcomingAPI
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUpcomingAPITest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upcomingRepository: UpcomingRepository

    private lateinit var getUpcomingAPI: GetUpcomingAPI

    @Before
    fun setup() {
        upcomingRepository = mockk()
        getUpcomingAPI = GetUpcomingAPI(upcomingRepository)
    }

    @Test
    fun `when call getUpcomingAPI, should return success response`() =
        coroutineRule.runBlockingTest {
            //Given
            every {
                runBlocking {
                    upcomingRepository.getUpcomingDataSource()
                }
            } returns mockApiResponseSuccessMovieListModel

            //When
            val actualData = getUpcomingAPI.invoke()

            //Then
            assertEquals(mockApiResponseSuccessMovieListModel, actualData)
        }

    @Test
    fun `when call getUpcomingAPI, should return failure response`() =
        coroutineRule.runBlockingTest {
            val mockFailure = getMockApiResponseFailure<MovieListModel>()
            //Given
            every {
                runBlocking {
                    upcomingRepository.getUpcomingDataSource()
                }
            } returns mockFailure

            //When
            val actualData = getUpcomingAPI.invoke()

            //Then
            assertEquals(mockFailure, actualData)
        }
}