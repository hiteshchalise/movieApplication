package com.hites.movieapplication.data.datasource.remote

import com.hites.movieapplication.data.model.MovieDetailsDTO
import com.hites.movieapplication.data.model.NowPlayingMovieDTO
import com.hites.movieapplication.data.model.PopularMovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/now_playing?api_key=7c67b6586378d5811be5e5fb14f222bf&language=en-US&page=1")
    fun fetchNowPlaying(): Call<List<NowPlayingMovieDTO>>

    @GET("movie/popular?api_key=7c67b6586378d5811be5e5fb14f222bf&language=en-US&page=1")
    fun fetchPopularMovies(): Call<List<PopularMovieDTO>>

    @GET("movie/{movieId}?api_key=7c67b6586378d5811be5e5fb14f222bf&language=en-US")
    fun fetchMovieDetails(@Path("movieId") id: Int): Call<MovieDetailsDTO>

}