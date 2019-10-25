package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.model.ApiResponse
import com.hites.movieapplication.data.model.Movie
import com.hites.movieapplication.data.model.ResultMovie
import io.reactivex.Observable
import retrofit2.Call

interface DataSource{
    fun getNowPlaying(): Call<ApiResponse>?
}
