package com.cinema.emovie.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cinema.emovie.config.MainCoroutineRule
import com.cinema.emovie.config.Mocks.mockUpcomingEntityList
import com.cinema.emovie.data.repository.upcoming.UpcomingRepository
import com.cinema.emovie.domain.upcoming.SetUpcomingLocal
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SetUpcomingLocalTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var upcomingRepository: UpcomingRepository

    private lateinit var setUpcomingLocal: SetUpcomingLocal

    @Before
    fun setup() {
        upcomingRepository = mockk(relaxed = true)
        setUpcomingLocal = SetUpcomingLocal(upcomingRepository)
    }

    @Test
    fun `when call invoke, should call upcomingRepository`() = coroutineRule.runBlockingTest {
        //When
        setUpcomingLocal.invoke(mockUpcomingEntityList)

        //Then
        verify {
            runBlocking {
                upcomingRepository.insertUpcomingLocal(mockUpcomingEntityList)
            }
        }
    }
}