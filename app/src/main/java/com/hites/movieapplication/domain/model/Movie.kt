package com.hites.movieapplication.domain.model

//data class Movie(
//    val adult: Boolean,
//    val id: Int,
//    val poster_path: String,
//    val title: String,
//    val vote_average: Double
//){
//    companion object{
//        val EMPTY = Movie(false, 0, "", "", 0.0)
//    }
//}
data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    companion object{
        val EMPTY = Movie(0, false, "", "", "", "", 0.0, "", "", "", false, 0.0, 0)
    }
}