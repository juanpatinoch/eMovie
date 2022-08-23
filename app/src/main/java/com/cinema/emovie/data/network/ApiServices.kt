package com.cinema.emovie.data.network

import com.cinema.emovie.data.model.MovieListModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiServices {

    @GET("/3/movie/upcoming")
    @Headers("language:es-CO", "Content-Type:application/json;charset=utf-8")
    suspend fun getUpcoming(
        @Header("Authorization") apiKey: String
    ): Response<MovieListModel>

    @GET("/3/movie/top_rated")
    @Headers("language:es-CO", "Content-Type:application/json;charset=utf-8")
    suspend fun getTopRated(
        @Header("Authorization") apiKey: String
    ): Response<MovieListModel>

    @GET("/3/trending/{media_type}/{time_window}")
    @Headers("language:es-CO", "Content-Type:application/json;charset=utf-8")
    suspend fun getTrending(
        @Header("Authorization") apiKey: String,
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String
    ): Response<MovieListModel>

}