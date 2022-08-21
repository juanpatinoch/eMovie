package com.cinema.emovie.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.toDatabaseDao
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.domain.get_trending.GetTrendingAPI
import com.cinema.emovie.domain.get_upcoming.GetUpcomingAPI
import com.cinema.emovie.domain.get_upcoming.SetUpcomingLocal
import com.cinema.emovie.domain.model.Movie
import com.cinema.emovie.domain.model.toDomain
import com.cinema.emovie.ui.home.status.HomeStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingAPI: GetUpcomingAPI,
    private val setUpcomingLocal: SetUpcomingLocal,
    private val getTrendingAPI: GetTrendingAPI
) : ViewModel() {

    private val _homeStatus = MutableLiveData<HomeStatus>()
    val homeStatus: LiveData<HomeStatus> = _homeStatus

    fun getInitData() {
        getUpcoming()
    }

    private fun getUpcoming() = viewModelScope.launch(Dispatchers.IO) {
        validateUpComingResponse(getUpcomingAPI.invoke())
    }

    private suspend fun validateUpComingResponse(response: ApiResponse<MovieListModel>) {
        when (response) {
            is ApiResponse.Success -> {
                setLocalData(response.data.toDomain())
                //setUIStatus(HomeStatus.SuccessGetUpcoming(response.data.toDomain()))
            }
            is ApiResponse.Failure -> {
                setUIStatus(HomeStatus.Failure(response.exception))
            }
        }
    }

    private suspend fun setLocalData(movies: List<Movie>?) {
        movies?.toDatabaseDao()?.let {
            setUpcomingLocal.invoke(it)
        }
    }

    private suspend fun setUIStatus(status: HomeStatus) = withContext(Dispatchers.Main) {
        _homeStatus.value = status
    }

}