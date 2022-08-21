package com.cinema.emovie.data.network.get_trending

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.network.ApiServices
import javax.inject.Inject

class GetTrendingDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getTrending(): ApiResponse<MovieListModel> {
        apiServices.getTrending(
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
    }
}