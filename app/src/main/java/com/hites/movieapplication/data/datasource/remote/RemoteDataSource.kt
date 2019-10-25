package com.hites.movieapplication.data.datasource.remote

import com.hites.movieapplication.data.datasource.DataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.model.ApiResponse
import com.hites.movieapplication.data.model.ResultMovie
import retrofit2.Call
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) : DataSource {

    override fun getNowPlaying(): Call<ApiResponse>? {
        return apiService.fetchNowPlaying()
    }

}