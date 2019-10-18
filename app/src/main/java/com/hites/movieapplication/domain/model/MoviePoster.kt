package com.hites.movieapplication.domain.model

data class MoviePoster(
    val adult: Boolean,
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double
)