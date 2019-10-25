package com.hites.movieapplication.data.model

data class ApiResponse(
    val dates: Dates,
    val page: Int,
    val movies: List<ResultMovie>,
    val total_pages: Int,
    val total_results: Int
)


