package com.cinema.emovie.ui.movie_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.local.entities.toGenreEntity
import com.cinema.emovie.data.local.entities.toTrailerEntity
import com.cinema.emovie.data.model.GenreListModel
import com.cinema.emovie.data.model.GenreModel
import com.cinema.emovie.data.model.TrailerListModel
import com.cinema.emovie.data.model.TrailerModel
import com.cinema.emovie.domain.genre.GetGenreAPI
import com.cinema.emovie.domain.genre.GetGenreLocal
import com.cinema.emovie.domain.genre.SetGenreLocal
import com.cinema.emovie.domain.model.toDomain
import com.cinema.emovie.domain.trailer.GetTrailerAPI
import com.cinema.emovie.domain.trailer.GetTrailerLocal
import com.cinema.emovie.domain.trailer.SetTrailerLocal
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
    private val getTrailerLocal: GetTrailerLocal,
    private val setTrailerLocal: SetTrailerLocal,
    private val getGenreAPI: GetGenreAPI,
    private val getTrailerAPI: GetTrailerAPI
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

    fun getTrailer(movieId: Int) {
        getTrailerDataLocal(movieId)
        getTrailerDataAPI(movieId)
    }

    private fun getTrailerDataLocal(movieId: Int?) = viewModelScope.launch(Dispatchers.Main) {
        getTrailerLocal.invoke(movieId).collect {
            setUIStatus(MovieDetailStatus.SuccessGetTrailer(it?.toDomain()))
        }
    }

    private fun getTrailerDataAPI(movieId: Int) = viewModelScope.launch(Dispatchers.IO) {
        validateGetTrailerDataAPIResponse(getTrailerAPI.invoke(movieId), movieId)
    }

    private suspend fun validateGetTrailerDataAPIResponse(
        response: ApiResponse<TrailerListModel>,
        movieId: Int
    ) {
        when (response) {
            is ApiResponse.Success -> {
                setTrailerDataLocal(response.data.trailerModelList, movieId)
            }
            is ApiResponse.Failure -> {
                setUIStatus(MovieDetailStatus.Error(response.exception))
            }
        }
    }

    private suspend fun setTrailerDataLocal(trailerModelList: List<TrailerModel>?, movieId: Int) {
        trailerModelList?.toTrailerEntity(movieId)?.let {
            setTrailerLocal.invoke(it)
        }
    }

    private suspend fun setUIStatus(status: MovieDetailStatus) = withContext(Dispatchers.Main) {
        _movieDetailStatus.value = status
    }
}