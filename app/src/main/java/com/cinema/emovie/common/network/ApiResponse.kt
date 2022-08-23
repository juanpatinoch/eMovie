package com.cinema.emovie.common.network

sealed class ApiResponse<out M> {

    data class Success<out M>(
        val data: M
    ) : ApiResponse<M>()

    data class Failure<out M>(
        val exception: Exception
    ) : ApiResponse<M>()
}
