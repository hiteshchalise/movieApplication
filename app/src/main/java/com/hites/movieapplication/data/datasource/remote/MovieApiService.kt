package com.hites.movieapplication.data.datasource.remote

import com.hites.movieapplication.data.model.ApiResponse
import com.hites.movieapplication.data.model.ResultMovie
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/now_playing?api_key=7c67b6586378d5811be5e5fb14f222bf&language=en-US&page=1")
    fun fetchNowPlaying(): Call<ApiResponse>?

}