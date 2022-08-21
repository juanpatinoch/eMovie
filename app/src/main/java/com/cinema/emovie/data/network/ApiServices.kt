package com.cinema.emovie.data.network

import com.cinema.emovie.data.model.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiServices {

    @GET("/3/trending/all/week")
    suspend fun getTrending(
        @Header("Authorization") apiKey: String
    ): Response<MovieListModel>

}