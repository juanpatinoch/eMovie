package com.cinema.emovie.common.network

sealed class ApiResponse<out M> {

    data class Success<out M>(
        val data: M
    ) : ApiResponse<M>()

    data class Failure<out M>(
        val exception: Exception
    ) : ApiResponse<M>()
}

/*data class ApiResponse<M>(
    @SerializedName("page")
    val page: Int?,
    val model: M?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)*/