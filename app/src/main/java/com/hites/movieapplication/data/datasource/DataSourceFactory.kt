package com.hites.movieapplication.data.datasource

import com.hites.movieapplication.data.datasource.local.LocalDataSource
import com.hites.movieapplication.data.datasource.local.MovieDao
import com.hites.movieapplication.data.datasource.remote.MovieApiService
import com.hites.movieapplication.data.datasource.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataSourceFactory @Inject constructor(private val movieApiService: MovieApiService,
                                            private val movieDao: MovieDao){

    fun getDataSource(cached: Boolean = false): DataSource {
        return if (!cached){
            RemoteDataSource(movieApiService, movieDao)
        } else{
            LocalDataSource(movieDao)
        }
    }
}