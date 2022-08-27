package com.cinema.emovie.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cinema.emovie.config.MainCoroutineRule
import com.cinema.emovie.config.Mocks.mockApiResponseSuccessMovieListModel
import com.cinema.emovie.config.Mocks.mockUpcomingEntityList
import com.cinema.emovie.data.local.entities.toUpcomingEntity
import com.cinema.emovie.domain.model.toDomain
import com.cinema.emovie.domain.top_rated.GetTopRatedAPI
import com.cinema.emovie.domain.top_rated.GetTopRatedLocal
import com.cinema.emovie.domain.top_rated.SetTopRatedLocal
import com.cinema.emovie.domain.trending.GetTrendingAPI
import com.cinema.emovie.domain.trending.GetTrendingLocal
import com.cinema.emovie.domain.trending.SetTrendingLocal
import com.cinema.emovie.domain.upcoming.GetUpcomingAPI
import com.cinema.emovie.domain.upcoming.GetUpcomingLocal
import com.cinema.emovie.domain.upcoming.SetUpcomingLocal
import com.cinema.emovie.ui.home.status.HomeStatus
import com.cinema.emovie.ui.home.viewmodel.HomeViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var observer: Observer<HomeStatus>

    private lateinit var getUpcomingLocal: GetUpcomingLocal
    private lateinit var setUpcomingLocal: SetUpcomingLocal
    private lateinit var getTopRatedLocal: GetTopRatedLocal
    private lateinit var setTopRatedLocal: SetTopRatedLocal
    private lateinit var getTrendingLocal: GetTrendingLocal
    private lateinit var setTrendingLocal: SetTrendingLocal
    private lateinit var getUpcomingAPI: GetUpcomingAPI
    private lateinit var getTopRatedAPI: GetTopRatedAPI
    private lateinit var getTrendingAPI: GetTrendingAPI

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        observer = mockk()
        getUpcomingLocal = mockk()
        setUpcomingLocal = mockk()
        getTopRatedLocal = mockk()
        setTopRatedLocal = mockk()
        getTrendingLocal = mockk()
        setTrendingLocal = mockk()
        getUpcomingAPI = mockk()
        getTopRatedAPI = mockk()
        getTrendingAPI = mockk()

        homeViewModel = HomeViewModel(
            getUpcomingLocal,
            setUpcomingLocal,
            getTopRatedLocal,
            setTopRatedLocal,
            getTrendingLocal,
            setTrendingLocal,
            getUpcomingAPI,
            getTopRatedAPI,
            getTrendingAPI
        )

        homeViewModel.homeStatus.observeForever(observer)
    }

    @After
    fun tearDown() {
        homeViewModel.homeStatus.removeObserver(observer)
    }

    @Test
    fun `when call getUpcomingDataLocal, should change observer status`() {
        val dataReturn = flowOf(mockUpcomingEntityList)
        //Given
        every {
            getUpcomingLocal.invoke()
        } returns dataReturn

        //When
        homeViewModel.getInitData("", "")

        //Then
        verify {
            observer.onChanged(HomeStatus.SuccessGetUpcoming(mockUpcomingEntityList.toDomain()))
        }
    }

    @Test
    fun `when call getUpcomingAPI, should return success`() = coroutineRule.runBlockingTest {
        //Given
        every {
            runBlocking {
                getUpcomingAPI.invoke()
            }
        } returns mockApiResponseSuccessMovieListModel

        //When
        homeViewModel.getInitData("", "")

        //Then
        verify {
            runBlocking {
                setUpcomingLocal.invoke(mockApiResponseSuccessMovieListModel.data.movieList!!.toUpcomingEntity()!!)
            }
        }
    }
}