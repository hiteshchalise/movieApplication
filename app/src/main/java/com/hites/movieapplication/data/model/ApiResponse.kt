package com.hites.movieapplication.data.model

data class ApiResponse<T>(
    val dates: Dates,
    val page: Int,
    val results: T,
    val total_pages: Int,
    val total_results: Int
)


