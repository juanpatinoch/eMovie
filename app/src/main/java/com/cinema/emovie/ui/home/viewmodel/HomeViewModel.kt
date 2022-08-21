package com.cinema.emovie.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.toDatabaseDao
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.model.MovieModel
import com.cinema.emovie.domain.upcoming.GetUpcomingAPI
import com.cinema.emovie.domain.upcoming.GetUpcomingLocal
import com.cinema.emovie.domain.upcoming.SetUpcomingLocal
import com.cinema.emovie.domain.model.toDomain
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
    private val getUpcomingAPI: GetUpcomingAPI
) : ViewModel() {

    private val _homeStatus = MutableLiveData<HomeStatus>()
    val homeStatus: LiveData<HomeStatus> = _homeStatus

    fun getInitData() {
        getUpcomingData()
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
        movies?.toDatabaseDao()?.let {
            setUpcomingLocal.invoke(it)
        }
    }

    private suspend fun setUIStatus(status: HomeStatus) = withContext(Dispatchers.Main) {
        _homeStatus.value = status
    }

}