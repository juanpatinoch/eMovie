package com.cinema.emovie.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cinema.emovie.config.MainCoroutineRule
import com.cinema.emovie.config.Mocks
import com.cinema.emovie.data.local.dao.UpcomingDao
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.network.upcoming.UpcomingDataSource
import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UpcomingRepositoryTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upcomingDataSource: UpcomingDataSource
    private lateinit var upcomingDao: UpcomingDao

    private lateinit var upcomingRepository: UpcomingRepository

    @Before
    fun setup() {
        upcomingDataSource = mockk()
        upcomingDao = mockk(relaxed = true)

        upcomingRepository = UpcomingRepository(upcomingDataSource, upcomingDao)
    }

    @Test
    fun `when call getUpcomingDataSource, should return success data`() =
        coroutineRule.runBlockingTest {
            //Given
            every {
                runBlocking {
                    upcomingDataSource.getUpcoming()
                }
            } returns Mocks.mockApiResponseSuccessMovieListModel

            //When
            val actualData = upcomingRepository.getUpcomingDataSource()

            //Then
            verify {
                runBlocking {
                    upcomingDataSource.getUpcoming()
                }
            }
            assertEquals(Mocks.mockApiResponseSuccessMovieListModel, actualData)
        }

    @Test
    fun `when call getUpcomingDataSource, should return success failure`() =
        coroutineRule.runBlockingTest {
            val failureData = Mocks.getMockApiResponseFailure<MovieListModel>()
            //Given
            every {
                runBlocking {
                    upcomingDataSource.getUpcoming()
                }
            } returns failureData

            //When
            val actualData = upcomingRepository.getUpcomingDataSource()

            //Then
            verify {
                runBlocking {
                    upcomingDataSource.getUpcoming()
                }
            }
            assertEquals(failureData, actualData)
        }

    @Test
    fun `when call getUpcomingLocal, should return mock data`() =
        coroutineRule.runBlockingTest {
            //Given
            every {
                upcomingDao.getAllUpcoming()
            } returns flowOf(Mocks.mockUpcomingEntityList)

            //When
            val actualData = upcomingRepository.getUpcomingLocal()

            //Then
            actualData.collect {
                assertEquals(Mocks.mockUpcomingEntityList, it)
            }
            verify {
                upcomingDao.getAllUpcoming()
            }
        }

    @Test
    fun `when call insertUpcomingLocal, should call insertAllUpcoming`() = coroutineRule.runBlockingTest {
        //When
        upcomingRepository.insertUpcomingLocal(Mocks.mockUpcomingEntityList)

        //Then
        verify {
            runBlocking {
                upcomingDao.insertAllUpcoming(Mocks.mockUpcomingEntityList)
            }
        }
    }
}