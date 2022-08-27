package com.cinema.emovie.data.network.trailer

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.TrailerListModel
import com.cinema.emovie.data.network.ApiServices
import javax.inject.Inject

class TrailerDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getTrailer(movieId: Int): ApiResponse<TrailerListModel> {
        try {
            apiServices.getTrailer(
                apiKeyModel.apiKey,
                movieId
            ).run {
                return when {
                    isSuccessful && body() != null -> {
                        ApiResponse.Success(body() as TrailerListModel)
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

    suspend fun getTrailerTv(movieId: Int): ApiResponse<TrailerListModel> {
        try {
            apiServices.getTrailerTv(
                apiKeyModel.apiKey,
                movieId
            ).run {
                return when {
                    isSuccessful && body() != null -> {
                        ApiResponse.Success(body() as TrailerListModel)
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