package com.cinema.emovie.data.network.upcoming

import android.util.Log
import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.network.ApiServices
import javax.inject.Inject

class UpcomingDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getUpcoming(): ApiResponse<MovieListModel> {
        try {
            apiServices.getUpcoming(
                apiKeyModel.apiKey
            ).run {
                return when {
                    isSuccessful && body() != null -> {
                        ApiResponse.Success(body() as MovieListModel)
                    }
                    else -> {
                        ApiResponse.Failure(errorBody() as Exception)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("API", "Route 3")
            return ApiResponse.Failure(e)
        }
    }

}