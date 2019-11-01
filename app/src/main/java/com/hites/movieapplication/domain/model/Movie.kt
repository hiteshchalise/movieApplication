package com.hites.movieapplication.domain.model

data class Movie(
    val adult: Boolean,
    val id: Int,
    val poster_path: String,
    val title: String,
    val vote_average: Double
){
    companion object{
        val EMPTY = Movie(false, 0, "", "", 0.0)
    }
}