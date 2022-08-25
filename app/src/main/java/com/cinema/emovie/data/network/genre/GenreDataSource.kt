package com.cinema.emovie.data.network.genre

import com.cinema.emovie.common.network.ApiResponse
import com.cinema.emovie.data.model.ApiKeyModel
import com.cinema.emovie.data.model.GenreListModel
import com.cinema.emovie.data.network.ApiServices
import javax.inject.Inject

class GenreDataSource @Inject constructor(
    private val apiServices: ApiServices,
    private val apiKeyModel: ApiKeyModel
) {

    suspend fun getGenre(): ApiResponse<GenreListModel> {
        try {
            apiServices.getGenre(
                apiKeyModel.apiKey
            ).run {
                return when {
                    isSuccessful && body() != null -> {
                        ApiResponse.Success(body() as GenreListModel)
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