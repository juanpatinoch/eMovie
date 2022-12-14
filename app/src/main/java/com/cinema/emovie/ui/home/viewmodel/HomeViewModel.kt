package com.cinema.emovie.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.toTopRatedEntity
import com.cinema.emovie.data.local.entities.toTrendingEntity
import com.cinema.emovie.data.local.entities.toUpcomingEntity
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.model.MovieModel
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingLocal: GetUpcomingLocal,
    private val setUpcomingLocal: SetUpcomingLocal,
    private val getTopRatedLocal: GetTopRatedLocal,
    private val setTopRatedLocal: SetTopRatedLocal,
    private val getTrendingLocal: GetTrendingLocal,
    private val setTrendingLocal: SetTrendingLocal,
    private val getUpcomingAPI: GetUpcomingAPI,
    private val getTopRatedAPI: GetTopRatedAPI,
    private val getTrendingAPI: GetTrendingAPI
) : ViewModel() {

    private val _homeStatus = MutableLiveData<HomeStatus>()
    val homeStatus: LiveData<HomeStatus> = _homeStatus

    fun getInitData(mediaType: String, timeWindow: String) {
        getUpcomingData()
        getTopRatedData()
        getTrendingData(mediaType, timeWindow)
    }

    private fun getUpcomingData() {
        getUpcomingDataLocal()
        getUpcomingDataAPI()
    }

    private fun getUpcomingDataLocal() = viewModelScope.launch(Dispatchers.Main) {
        getUpcomingLocal.invoke().collect {
            setUIStatus(HomeStatus.SuccessGetUpcoming(it.toDomain()))
        }
    }

    private fun getUpcomingDataAPI() = viewModelScope.launch(Dispatchers.IO) {
        validateUpComingDataAPIResponse(getUpcomingAPI.invoke())
    }

    private suspend fun validateUpComingDataAPIResponse(response: ApiResponse<MovieListModel>) {
        when (response) {
            is ApiResponse.Success -> {
                setUpcomingDataLocal(response.data.movieList)
            }
            is ApiResponse.Failure -> {
                setUIStatus(HomeStatus.Error(response.exception))
            }
        }
    }

    private suspend fun setUpcomingDataLocal(movies: List<MovieModel>?) {
        movies?.toUpcomingEntity()?.let {
            setUpcomingLocal.invoke(it)
        }
    }

    private fun getTopRatedData() {
        getTopRatedDataLocal()
        getTopRatedDataAPI()
    }

    private fun getTopRatedDataLocal() = viewModelScope.launch(Dispatchers.Main) {
        getTopRatedLocal.invoke().collect {
            setUIStatus(HomeStatus.SuccessGetTopRated(it.toDomain()))
        }
    }

    private fun getTopRatedDataAPI() = viewModelScope.launch(Dispatchers.IO) {
        validateGetTopRatedDataAPIResponse(getTopRatedAPI.invoke())
    }

    private suspend fun validateGetTopRatedDataAPIResponse(response: ApiResponse<MovieListModel>) {
        when (response) {
            is ApiResponse.Success -> {
                setTopRatedDataLocal(response.data.movieList)
            }
            is ApiResponse.Failure -> {
                setUIStatus(HomeStatus.Error(response.exception))
            }
        }
    }

    private suspend fun setTopRatedDataLocal(movies: List<MovieModel>?) {
        movies?.toTopRatedEntity()?.let {
            setTopRatedLocal.invoke(it)
        }
    }

    fun getTrendingData(mediaType: String, timeWindow: String) {
        getTrendingDataLocal()
        getTrendingDataAPI(mediaType, timeWindow)
    }

    private fun getTrendingDataLocal() = viewModelScope.launch(Dispatchers.Main) {
        getTrendingLocal.invoke().collect {
            setUIStatus(HomeStatus.SuccessGetTrending(it.toDomain()))
        }
    }

    private fun getTrendingDataAPI(mediaType: String, timeWindow: String) =
        viewModelScope.launch(Dispatchers.IO) {
            validateGetTrendingDataAPIResponse(getTrendingAPI.invoke(mediaType, timeWindow))
        }

    private suspend fun validateGetTrendingDataAPIResponse(response: ApiResponse<MovieListModel>) {
        when (response) {
            is ApiResponse.Success -> {
                setTrendingDataLocal(response.data.movieList)
            }
            is ApiResponse.Failure -> {
                setUIStatus(HomeStatus.Error(response.exception))
            }
        }
    }

    private suspend fun setTrendingDataLocal(movies: List<MovieModel>?) {
        movies?.toTrendingEntity()?.let {
            setTrendingLocal.invoke(it)
        }
    }

    private suspend fun setUIStatus(status: HomeStatus) = withContext(Dispatchers.Main) {
        _homeStatus.value = status
    }

}