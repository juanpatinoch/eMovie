package com.cinema.emovie.data.network

import com.cinema.emovie.data.model.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiServices {

    @GET("/3/movie/upcoming")
    @Headers("language:es-CO", "Content-Type:application/json", "charset=utf-8")
    suspend fun getUpcoming(
        @Header("Authorization") apiKey: String
    ): Response<MovieListModel>

    @GET("/3/trending/all/week")
    @Headers("language:es-CO", "Content-Type:application/json", "charset=utf-8")
    suspend fun getTrending(
        @Header("Authorization") apiKey: String
    ): Response<MovieListModel>

}