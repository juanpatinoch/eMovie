package com.cinema.emovie.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cinema.emovie.domain.get_trending.GetTrendingAPI
import com.cinema.emovie.domain.get_upcoming.GetUpcomingAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingAPI: GetUpcomingAPI,
    private val getTrendingAPI: GetTrendingAPI
) : ViewModel() {

    fun getUp() = viewModelScope.launch {
        val result = getUpcomingAPI.invoke()
        Log.e("Data API", result.toString())
    }


    fun getTrending() = viewModelScope.launch {
        val result = getTrendingAPI.invoke()
        Log.e("Data API", result.toString())
    }

}