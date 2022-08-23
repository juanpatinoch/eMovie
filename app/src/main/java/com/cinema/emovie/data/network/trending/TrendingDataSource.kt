package com.cinema.emovie.data.network.trending

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.network.ApiServices
import javax.inject.Inject

class TrendingDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getTrending(mediaType: String, timeWindow: String): ApiResponse<MovieListModel> {
        try {
            apiServices.getTrending(
                apiKeyModel.apiKey,
                mediaType,
                timeWindow
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
            return ApiResponse.Failure(e)
        }
    }

}