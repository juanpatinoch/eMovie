package com.cinema.emovie.data.network.top_rated

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.MovieListModel
import com.cinema.emovie.data.network.ApiServices
import retrofit2.HttpException
import javax.inject.Inject

class TopRatedDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getTopRated(): ApiResponse<MovieListModel> {
        try {
            apiServices.getTopRated(
                apiKeyModel.apiKey
            ).run {
                return when {
                    isSuccessful && body() != null -> {
                        ApiResponse.Success(body() as MovieListModel)
                    }
                    else -> {
                        ApiResponse.Failure(this as HttpException)
                    }
                }
            }
        } catch (e: Exception) {
            return ApiResponse.Failure(e)
        }
    }

}