package com.cinema.emovie.ui.movie_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.toGenreEntity
import com.cinema.emovie.data.model.GenreListModel
import com.cinema.emovie.data.model.GenreModel
import com.cinema.emovie.domain.genre.GetGenreAPI
import com.cinema.emovie.domain.genre.GetGenreLocal
import com.cinema.emovie.domain.genre.SetGenreLocal
import com.cinema.emovie.ui.movie_detail.status.MovieDetailStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getGenreLocal: GetGenreLocal,
    private val setGenreLocal: SetGenreLocal,
    private val getGenreAPI: GetGenreAPI
) : ViewModel() {

    private val _movieDetailStatus = MutableLiveData<MovieDetailStatus>()
    val movieDetailStatus: LiveData<MovieDetailStatus> = _movieDetailStatus

    fun getGenre(genreIdList: List<Int>?) {
        getGenreDataLocal(genreIdList)
        getGenreDataAPI()
    }

    private fun getGenreDataLocal(genreIdList: List<Int>?) =
        viewModelScope.launch(Dispatchers.Main) {
            getGenreLocal.invoke(genreIdList).collect {
                setUIStatus(MovieDetailStatus.SuccessGetGenre(it.joinToString(" · ")))
            }
        }

    private fun getGenreDataAPI() = viewModelScope.launch(Dispatchers.IO) {
        validateGetGenreDataAPIResponse(getGenreAPI.invoke())
    }

    private suspend fun validateGetGenreDataAPIResponse(response: ApiResponse<GenreListModel>) {
        when (response) {
            is ApiResponse.Success -> {
                setGenreDataLocal(response.data.genreList)
            }
            is ApiResponse.Failure -> {
                setUIStatus(MovieDetailStatus.Error(response.exception))
            }
        }
    }

    private suspend fun setGenreDataLocal(genreModelList: List<GenreModel>) {
        genreModelList.toGenreEntity()?.let {
            setGenreLocal.invoke(it)
        }
    }

    private suspend fun setUIStatus(status: MovieDetailStatus) = withContext(Dispatchers.Main) {
        _movieDetailStatus.value = status
    }
}